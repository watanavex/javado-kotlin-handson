package jp.watanave.githubsample.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import jp.watanave.githubsample.R
import jp.watanave.githubsample.data.Api
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            val api = retrofit.create(Api::class.java)

            this.recyclerView.visibility = View.INVISIBLE
            this.progressBar.visibility = View.VISIBLE

            GlobalScope.launch {
                val response = api.search(editText.text.toString()).execute()
                val items = response.body()?.items ?: emptyList()

                runOnUiThread {
                    adapter.refreshData(items)
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }
}