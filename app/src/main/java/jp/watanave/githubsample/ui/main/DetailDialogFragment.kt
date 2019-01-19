package jp.watanave.githubsample.ui.main

import android.app.Dialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import com.shopify.livedataktx.nonNull
import com.shopify.livedataktx.observe
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import jp.watanave.githubsample.R
import jp.watanave.githubsample.databinding.DialogDetailBinding
import jp.watanave.githubsample.di.ViewModelFactory
import javax.inject.Inject

class DetailDialogFragment: DialogFragment() {
    companion object {
        private const val ARG_KEY_REPO_ID = "ARG_KEY_REPO_ID"
        fun newInstance(repositoryId: Int): DetailDialogFragment {
            val arguments = Bundle().also {
                it.putInt(ARG_KEY_REPO_ID, repositoryId)
            }
            return DetailDialogFragment()
                .also { it.arguments = arguments }
        }
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory
    lateinit var binding: DialogDetailBinding
    private val store: MainStore by lazy {
        this.viewModelFactory.get(this.activity!!, MainStore::class).also {
            Log.i("@@@ Fragment", "store $it")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.i("@@@ Fragment", "onCreateDialog")
        AndroidSupportInjection.inject(this)

        val builder = AlertDialog.Builder(this.activity!!)
        this.binding = DataBindingUtil.inflate(
            LayoutInflater.from(this.activity),
            R.layout.dialog_detail,
            null,
            false
        )
        builder.setView(this.binding.root)
        builder.setPositiveButton("Close") { _, _ -> }

        val repoId = this.arguments?.getInt(ARG_KEY_REPO_ID) ?: throw IllegalArgumentException()

        this.store.repositorySearchResult
            .nonNull()
            .observe(this) { repositories ->
                val repository = repositories.firstOrNull { it.id == repoId }
                if (repository == null) {
                    Log.i("@@@ Fragment", "null !!!!")
                    return@observe
                }
                this.binding.repoNameTextView.text = repository.name
                this.binding.ownerNameTextView.text = repository.owner.login
                this.binding.descTextView.text = repository.description
                Picasso.get().load(repository.owner.avatarUrl).into(this.binding.imageView)
            }

        return builder.create()
    }
}