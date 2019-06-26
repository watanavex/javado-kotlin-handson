package jp.watanave.githubsample.ui.main

import jp.watanave.githubsample.data.GithubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface StateChangeListener {
    fun onChange(state: MainState)
}

class MainViewModel(val githubApi: GithubApi) {

    var listener: StateChangeListener? = null
    var currentState: MainState = MainState(false, false, emptyList(), "")
        private set

    private fun notifyCurrentState() {
        this.listener?.onChange(this.currentState)
    }

    fun checkSearchable(searchWord: String) {
        val searchable = searchWord.count() > 2
        currentState = currentState.copy(searchable = searchable)
        this.notifyCurrentState()
    }

    fun searchRepository(searchWord: String) {
        currentState = currentState.copy(isLoading = true, message = "")
        this.notifyCurrentState()

        GlobalScope.launch {
            try {
                val response = githubApi.search(searchWord)
                val items = response?.items ?: emptyList()

                Dispatchers.Main
                launch(Dispatchers.Main) {
                    currentState = currentState.copy(isLoading = false, repositories = items)
                    notifyCurrentState()
                }
            }
            catch (e: Throwable) {
                launch(Dispatchers.Main) {
                    currentState = currentState.copy(isLoading = false, message = e.localizedMessage)
                    notifyCurrentState()
                }
            }
        }

    }


}