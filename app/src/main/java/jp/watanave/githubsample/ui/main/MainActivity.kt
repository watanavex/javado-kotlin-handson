package jp.watanave.githubsample.ui.main

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxbinding3.view.clicks
import com.shopify.livedataktx.nonNull
import com.shopify.livedataktx.observe
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jp.watanave.githubsample.R
import jp.watanave.githubsample.databinding.ActivityMainBinding
import jp.watanave.githubsample.di.ViewModelFactory
import jp.watanave.githubsample.di.ViewModelKey
import jp.watanave.githubsample.flux.LoadingState
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var actionCreator: MainActionCreator

    private val compositeDisposable = CompositeDisposable()
    private val adapter = RepositoryListAdapter()
    private val store: MainStore by lazy {
        this.viewModelFactory.get(this, MainStore::class).also {
            Log.i("@@@ Activity", "store $it")
        }
    }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private fun setupRecyclerView() {
        this.binding.recyclerView.adapter = this.adapter
        this.binding.recyclerView.layoutManager = LinearLayoutManager(this)
        this.binding.recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        this.adapter.itemClickPublisher
            .subscribe {
                val fragment = DetailDialogFragment.newInstance(it.id)
                fragment.show(this.supportFragmentManager, DetailDialogFragment::class.simpleName)
            }
            .addTo(this.compositeDisposable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupRecyclerView()
        Log.i("@@@ Activity", "onCreate")

        this.store.repositorySearchResult
            .nonNull()
            .observe(this) {
                Log.i("@@@ Activity", "repositorySearchResult")
                this.adapter.refreshData(it)
            }
        this.store.loadingStateChanged
            .nonNull()
            .observe(this) { state: LoadingState ->
                when (state) {
                    LoadingState.Loaded -> {
                        this.binding.recyclerView.visibility = View.VISIBLE
                        this.binding.progressBar.visibility = View.INVISIBLE
                    }
                    LoadingState.Loading -> {
                        this.binding.recyclerView.visibility = View.INVISIBLE
                        this.binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }

        this.binding.button.setOnClickListener {
            val text = binding.editText.text
            if (text.isBlank()) {
                return@setOnClickListener
            }
        }
        this.binding.button.clicks()
            .throttleLast(1000, TimeUnit.MILLISECONDS)
            .map { binding.editText.text }
            .filter(Editable::isNotBlank)
            .subscribe { searchText ->
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(this.binding.root.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                this.actionCreator.searchRepository(searchText.toString())
            }
            .addTo(this.compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.compositeDisposable.clear()
    }
}

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainStore::class)
    abstract fun bindMainStore(viewModel: MainStore): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeFirstFragment(): DetailDialogFragment
}