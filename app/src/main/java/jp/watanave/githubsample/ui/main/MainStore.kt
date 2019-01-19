package jp.watanave.githubsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import jp.watanave.githubsample.data.Repository
import jp.watanave.githubsample.flux.Action
import jp.watanave.githubsample.flux.Dispatcher
import jp.watanave.githubsample.flux.LoadingState
import jp.watanave.githubsample.util.toLiveData
import javax.inject.Inject

class MainStore @Inject constructor(private val dispatcher: Dispatcher): ViewModel() {

    val repositorySearchResult: LiveData<List<Repository>> = this.dispatcher
        .actionPublisher<Action.RepositoryLoaded>()
        .map(Action.RepositoryLoaded::repositories)
        .toLiveData()

    val loadingStateChanged: LiveData<LoadingState> = this.dispatcher
        .actionPublisher<Action.LoadingStateChanged>()
        .map(Action.LoadingStateChanged::state)
        .toLiveData()
}