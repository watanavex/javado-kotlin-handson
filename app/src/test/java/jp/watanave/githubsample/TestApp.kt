package jp.watanave.githubsample

import io.mockk.mockk
import jp.watanave.githubsample.ui.main.MainViewModel
import jp.watanave.githubsample.util.Dispatchers

class TestApp: App() {
    override val viewModel: MainViewModel = mockk(relaxUnitFun = true)
}

class TestDispatchers: Dispatchers() {

    override fun io() = kotlinx.coroutines.Dispatchers.Unconfined
    override fun main() = kotlinx.coroutines.Dispatchers.Unconfined

}