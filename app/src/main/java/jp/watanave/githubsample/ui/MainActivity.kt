package jp.watanave.githubsample.ui

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.util.Log
import dagger.Binds
import dagger.Module
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.IntoMap
import jp.watanave.githubsample.R
import jp.watanave.githubsample.data.Api
import jp.watanave.githubsample.di.ViewModelFactory
import jp.watanave.githubsample.di.ViewModelKey
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var api: Api

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MainViewModel by lazy {
        this.viewModelFactory.get(this, MainViewModel::class)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val response = api.search("UserDefaultsProxy")
                .execute()
            response.body()?.let {
                Log.i("@@@R", it.items.toString())
            }
            response.errorBody()?.let {
                Log.i("@@@E", it.toString())
            }
        }

        this.viewModel.exec()
        this.viewModel.exec()
        this.viewModel.exec()
    }
}

@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainStore(viewModel: MainViewModel): ViewModel
}

class MainViewModel @Inject constructor(val testModule: TestModule): ViewModel() {
    fun exec() = this.testModule.exec()
}

class TestModule @Inject constructor() {

    private var count = 0

    fun exec() {
        this.count += 1
        Log.i("@@@", this.count.toString())
    }
}