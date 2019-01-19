package jp.watanave.githubsample.ui.main

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import jp.watanave.githubsample.R
import jp.watanave.githubsample.data.Repository
import jp.watanave.githubsample.databinding.RepositoryItemBinding

class RepositoryListAdapter: RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private var repositories = emptyList<Repository>()
    private val itemClickSubject = PublishSubject.create<Repository>()
    val itemClickPublisher: Observable<Repository>
        get() = this.itemClickSubject.hide()

    fun refreshData(repositories: List<Repository>) {
        this.repositories = repositories
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<RepositoryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.repository_item,
            parent,
            false
        )
        return ViewHolder(binding).also { viewHolder ->
            binding.root.clicks()
                .map { viewHolder.adapterPosition }
                .map { this.repositories[it] }
                .subscribe(this.itemClickSubject)
        }
    }

    override fun getItemCount(): Int = this.repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = this.repositories[position]
        holder.binding.textView.text = repo.name
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.itemClickSubject.onComplete()
    }

    class ViewHolder(val binding: RepositoryItemBinding): RecyclerView.ViewHolder(binding.root)
}