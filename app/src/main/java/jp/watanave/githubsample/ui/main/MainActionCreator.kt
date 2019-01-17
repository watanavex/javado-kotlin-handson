package jp.watanave.githubsample.ui.main

import jp.watanave.githubsample.data.Api
import jp.watanave.githubsample.di.ActivityScope
import jp.watanave.githubsample.flux.Action
import jp.watanave.githubsample.flux.Dispatcher
import jp.watanave.githubsample.flux.LoadingState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class MainActionCreator @Inject constructor(private val dispatcher: Dispatcher,
                                            private val api: Api) {

    fun searchRepository(searchText: String) {
        GlobalScope.launch {
            dispatcher.dispatch(Action.LoadingStateChanged(LoadingState.Loading))

            val result = api.search(searchText).execute()
            result.body()?.items?.let { repositories ->
                dispatcher.dispatch(Action.RepositoryLoaded(repositories))
            }

            dispatcher.dispatch(Action.LoadingStateChanged(LoadingState.Loaded))
        }
    }

}