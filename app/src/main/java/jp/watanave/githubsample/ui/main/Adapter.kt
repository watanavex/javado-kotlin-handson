package jp.watanave.githubsample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.watanave.githubsample.R
import jp.watanave.githubsample.data.Repository
import kotlinx.android.synthetic.main.repository_item.view.*

class RepositoryListAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private var repositories = emptyList<Repository>()
    var didSelectItem: ((Repository)->Unit)? = null

    fun refreshData(repositories: List<Repository>) {
        this.repositories = repositories
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)

        val viewHolder = ViewHolder(view)
        view.root.setOnClickListener {
            val index = viewHolder.adapterPosition
            val repository = this.repositories[index]
            this.didSelectItem?.invoke(repository)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = this.repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = this.repositories[position]
        holder.view.textView.text = repo.name
    }

    class ViewHolder(val view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view)
}