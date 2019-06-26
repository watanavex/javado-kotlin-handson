package jp.watanave.githubsample.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.watanave.githubsample.App
import jp.watanave.githubsample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val adapter = RepositoryListAdapter()
    val viewModel: MainViewModel by lazy {
        App.instance.viewModel
    }

    private fun setupRecyclerView() {
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
        this.recyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        this.adapter.didSelectItem = { repository ->
            val fragment = DetailDialogFragment.newInstance(
                repository.name,
                repository.owner.login,
                repository.description,
                repository.owner.avatarUrl)
            fragment.show(this.supportFragmentManager, DetailDialogFragment::class.qualifiedName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        this.setupRecyclerView()

        //
        // ViewModelへ命令
        //
        this.editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.checkSearchable(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        this.searchButton.setOnClickListener {
            viewModel.searchRepository(this.editText.text.toString())
        }

        //
        // Stateで表示更新
        //
        this.viewModel.listener = object: StateChangeListener {
            override fun onChange(state: MainState) = render(state)
        }
    }

    fun render(state: MainState) {
        this.searchButton.isEnabled = state.searchable

        this.messageTextView.text = state.message

        if (state.isLoading) {
            this.recyclerView.visibility = View.INVISIBLE
            this.progressBar.visibility = View.VISIBLE
        }
        else {
            this.recyclerView.visibility = View.VISIBLE
            this.progressBar.visibility = View.INVISIBLE
        }

        this.adapter.refreshData(state.repositories)
    }
}