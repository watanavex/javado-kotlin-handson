package jp.watanave.githubsample

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import jp.watanave.githubsample.data.GithubApi
import jp.watanave.githubsample.data.RepositoryResponse
import jp.watanave.githubsample.ui.main.MainViewModel
import jp.watanave.githubsample.util.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeoutException

class MainViewModelTest {

    lateinit var viewModel: MainViewModel
    lateinit var githubApi: GithubApi
    lateinit var dispatchers: Dispatchers

    @Before
    fun setup() {
        this.githubApi = mockk()
        this.dispatchers = spyk(TestDispatchers())
        this.viewModel = MainViewModel(this.githubApi, this.dispatchers)
    }

    @Test
    fun `検索文字が3文字未満だと検索ボタンがDisableになること`() {
        this.viewModel.checkSearchable("ho")
        Assert.assertEquals(false, this.viewModel.currentState.searchable)
    }

    @Test
    fun `検索文字が3文字以上だと検索ボタンがEnableになること`() {
        this.viewModel.checkSearchable("hogehoge")
        Assert.assertEquals(true, this.viewModel.currentState.searchable)
    }

    @Test
    fun `検索開始するとProgressBarがくるくるすること`() {
        every { dispatchers.io() } returns kotlinx.coroutines.Dispatchers.IO
        every { githubApi.search(any()) } answers {
            while (true) { Thread.sleep(1_000) }; mockk()
        }

        this.viewModel.searchRepository("hogehoge")
        Assert.assertEquals(true, this.viewModel.currentState.isLoading)
    }

    @Test
    fun `検索が終わるとProgressBarが消えること`() {
        every { githubApi.search(any()) } returns RepositoryResponse(emptyList())

        this.viewModel.searchRepository("hogehoge")
        Assert.assertEquals(false, this.viewModel.currentState.isLoading)
    }

    @Test
    fun `検索に失敗するとエラーメッセージを表示すること`() {
        val throwable = TimeoutException("hoge")
        every { githubApi.search(any()) } throws throwable

        this.viewModel.searchRepository("hogehoge")
        Assert.assertEquals(throwable.localizedMessage, this.viewModel.currentState.message)
    }
}