package jp.watanave.githubsample

import io.mockk.mockk
import jp.watanave.githubsample.data.GithubApi

class TestApp: App() {
    override val githubApi: GithubApi
        get() = mockk()
}