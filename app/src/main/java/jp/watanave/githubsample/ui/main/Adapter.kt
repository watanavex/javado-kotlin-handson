package jp.watanave.githubsample.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import jp.watanave.githubsample.R
import jp.watanave.githubsample.data.Repository
import jp.watanave.githubsample.databinding.RepositoryItemBinding

class RepositoryListAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    private var repositories = emptyList<Repository>()

    // TODO: [2] リストビューのアイテムがタップされた時のコールバックを登録できるようにする
    /*
    Repository型を引数に受ける関数型をプロパティで定義しておく
    */

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

        val viewHolder = ViewHolder(binding)
        binding.root.setOnClickListener {
            val index = viewHolder.adapterPosition
            val repository = this.repositories[index]
            // TODO: [3] 2で定義したコールバックを呼び出す
        }

        return viewHolder
    }

    override fun getItemCount(): Int = this.repositories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = this.repositories[position]
        holder.binding.textView.text = repo.name
    }

    class ViewHolder(val binding: RepositoryItemBinding): androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)
}