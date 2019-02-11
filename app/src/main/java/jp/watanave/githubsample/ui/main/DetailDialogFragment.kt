package jp.watanave.githubsample.ui.main

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import jp.watanave.githubsample.R
import jp.watanave.githubsample.databinding.DialogDetailBinding
import jp.watanave.githubsample.util.setImageSource

class DetailDialogFragment: androidx.fragment.app.DialogFragment() {

    companion object {
        private const val KEY_REPOSITORY_NAME = "KEY_REPOSITORY_NAME"
        private const val KEY_REPOSITORY_OWNER = "KEY_REPOSITORY_OWNER"
        private const val KEY_REPOSITORY_DESCRIPTION = "KEY_REPOSITORY_DESCRIPTION"
        private const val KEY_REPOSITORY_AVATAR_URL = "KEY_REPOSITORY_AVATAR_URL"
        fun newInstance(name: String, owner: String, description: String, avatarUrl: String): DetailDialogFragment {
            val arguments = Bundle().also {
                it.putString(KEY_REPOSITORY_NAME, name)
                it.putString(KEY_REPOSITORY_OWNER, owner)
                it.putString(KEY_REPOSITORY_DESCRIPTION, description)
                it.putString(KEY_REPOSITORY_AVATAR_URL, avatarUrl)
            }
            return DetailDialogFragment()
                .also { it.arguments = arguments }
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(this.activity!!)

        val binding = DataBindingUtil.inflate<DialogDetailBinding>(
            LayoutInflater.from(this.activity),
            R.layout.dialog_detail,
            null,
            false
        )
        builder.setView(binding.root)
        builder.setPositiveButton("Close") { _, _ -> }

        binding.repoNameTextView.text = this.arguments?.getString(KEY_REPOSITORY_NAME)
        binding.ownerNameTextView.text = this.arguments?.getString(KEY_REPOSITORY_OWNER)
        binding.descTextView.text = this.arguments?.getString(KEY_REPOSITORY_DESCRIPTION)
        binding.imageView.setImageSource(this.arguments?.getString(KEY_REPOSITORY_AVATAR_URL))

        return builder.create()
    }
}