package jp.watanave.githubsample

import io.mockk.mockk
import jp.watanave.githubsample.ui.main.MainViewModel

class TestApp: App() {
    override val viewModel: MainViewModel = mockk(relaxUnitFun = true)
}