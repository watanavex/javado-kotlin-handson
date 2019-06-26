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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val adapter = RepositoryListAdapter()

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

        this.editText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when  {
                    s == null -> searchButton.isEnabled = false
                    s.count() < 3 -> searchButton.isEnabled = false
                    else -> searchButton.isEnabled = true
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        this.searchButton.setOnClickListener {
            val githubApi = App.instance.githubApi

            this.recyclerView.visibility = View.INVISIBLE
            this.progressBar.visibility = View.VISIBLE

            GlobalScope.launch {
                try {
                    val response = githubApi.search(editText.text.toString())
                    val items = response?.items ?: emptyList()

                    runOnUiThread {
                        adapter.refreshData(items)
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }
                }
                catch (e: Throwable) {
                    runOnUiThread {
                        messageTextView.text = e.localizedMessage
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}