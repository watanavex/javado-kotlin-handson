package jp.watanave.githubsample.ui.main

import jp.watanave.githubsample.data.Repository

data class MainState(val searchable: Boolean,
                     val isLoading: Boolean,
                     val repositories: List<Repository>,
                     val message: String)