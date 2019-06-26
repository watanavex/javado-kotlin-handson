package jp.watanave.githubsample

import android.os.Build
import android.view.View
import io.mockk.every
import jp.watanave.githubsample.data.RepositoryResponse
import jp.watanave.githubsample.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import java.util.concurrent.TimeoutException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApp::class)
class MainActivityTest {

    lateinit var activityController: ActivityController<MainActivity>
    lateinit var activity: MainActivity

    @Before
    fun setup() {
        this.activityController = Robolectric.buildActivity(MainActivity::class.java)
        this.activity = this.activityController.get()

        // 必要であれば
        this.activityController.create()
        this.activityController.start()
        this.activityController.resume()
    }

    //
    // searchButton.isEnabled のリファクタリングが気軽になる
    //
    @Test
    fun `初期状態`() {
        Assert.assertEquals(0, this.activity.editText.text.count())
        Assert.assertEquals(false, this.activity.searchButton.isEnabled)
        Assert.assertEquals(View.VISIBLE, this.activity.recyclerView.visibility)
        Assert.assertEquals(View.INVISIBLE, this.activity.progressBar.visibility)
    }

    @Test
    fun `検索文字が3文字未満だと検索ボタンがDisableになること`() {
        this.activity.editText.text.insert(0, "ho")
        Assert.assertEquals(false, this.activity.searchButton.isEnabled)
    }

    @Test
    fun `検索文字が3文字以上だと検索ボタンがEnableになること`() {
        this.activity.editText.text.insert(0, "hogehoge")
        Assert.assertEquals(true, this.activity.searchButton.isEnabled)
    }

    @Test
    fun `検索ボタンを押すとProgressBarがくるくるすること`() {
        this.activity.editText.text.insert(0, "hogehoge")
        this.activity.searchButton.performClick()

        every { App.instance.githubApi.search(any()) } returns RepositoryResponse(emptyList())

        Assert.assertEquals(View.VISIBLE, this.activity.progressBar.visibility)
        Assert.assertEquals(View.INVISIBLE, this.activity.recyclerView.visibility)
    }

    //
    // タイミング依存の問題はいまだに残る 😥
    //
    @Test
    fun `検索が終わるとProgressBarが消えること`() {
        this.activity.editText.text.insert(0, "hogehoge")
        this.activity.searchButton.performClick()

        every { App.instance.githubApi.search(any()) } returns RepositoryResponse(emptyList())

        Assert.assertEquals(View.INVISIBLE, this.activity.progressBar.visibility)
        Assert.assertEquals(View.VISIBLE, this.activity.recyclerView.visibility)
    }

    //
    // どうやってエラーにする？
    //
    @Test
    fun `検索に失敗するとエラーメッセージが表示されること`() {
        this.activity.editText.text.insert(0, "hogehoge")
        this.activity.searchButton.performClick()

        val exception = TimeoutException()
        every { App.instance.githubApi.search(any()) } throws exception

        Assert.assertEquals(View.INVISIBLE, this.activity.progressBar.visibility)
        Assert.assertEquals(View.VISIBLE, this.activity.recyclerView.visibility)
        Assert.assertEquals(exception.localizedMessage, this.activity.messageTextView.text)
    }
}