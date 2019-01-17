package jp.watanave.githubsample.flux

import jp.watanave.githubsample.data.Repository

sealed class Action {

    data class LoadingStateChanged(val state: LoadingState): Action()
    data class RepositoryLoaded(val repositories: List<Repository>): Action()

}

enum class LoadingState {
    Loading, Loaded
}