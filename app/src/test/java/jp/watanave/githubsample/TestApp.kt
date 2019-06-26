package jp.watanave.githubsample

import jp.watanave.githubsample.data.Api
import jp.watanave.githubsample.data.StubApi

class TestApp: App() {
    override val api: Api
        get() = StubApi()
}