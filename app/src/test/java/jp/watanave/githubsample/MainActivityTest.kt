package jp.watanave.githubsample

import android.os.Build
import android.view.View
import io.mockk.verify
import jp.watanave.githubsample.ui.main.MainActivity
import jp.watanave.githubsample.ui.main.MainState
import jp.watanave.githubsample.ui.main.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], application = TestApp::class)
class MainActivityTest {

    lateinit var activityController: ActivityController<MainActivity>
    lateinit var activity: MainActivity
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        this.viewModel = App.instance.viewModel
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
    fun `検索文字を変更したらViewModelのcheckSearchableが呼ばれること`() {
        this.activity.editText.text.insert(0, "ho")

        verify(exactly = 1) { viewModel.checkSearchable("ho") }
    }

    @Test
    fun `検索ボタンをタップしたらViewModelのsearchRepositoryが呼ばれること`() {
        val state = MainState(true, false, emptyList(), "")
        this.activity.render(state)
        this.activity.editText.text.insert(0, "hoge")

        this.activity.searchButton.performClick()
        verify(exactly = 1) { viewModel.searchRepository("hoge") }
    }

    @Test
    fun `検索不可状態だと検索ボタンがDisableになること`() {
        val state = MainState(false, false, emptyList(), "")
        this.activity.render(state)

        Assert.assertEquals(false, this.activity.searchButton.isEnabled)
    }

    @Test
    fun `検索可能状態だと検索ボタンがEnableになること`() {
        val state = MainState(true, false, emptyList(), "")
        this.activity.render(state)

        Assert.assertEquals(true, this.activity.searchButton.isEnabled)
    }

    @Test
    fun `検索中はProgressBarがくるくるすること`() {
        val state = MainState(true, true, emptyList(), "")
        this.activity.render(state)

        Assert.assertEquals(View.VISIBLE, this.activity.progressBar.visibility)
        Assert.assertEquals(View.INVISIBLE, this.activity.recyclerView.visibility)
    }

    @Test
    fun `検索中でなければProgressBarが消えること`() {
        val state = MainState(true, false, emptyList(), "")
        this.activity.render(state)

        Assert.assertEquals(View.INVISIBLE, this.activity.progressBar.visibility)
        Assert.assertEquals(View.VISIBLE, this.activity.recyclerView.visibility)
    }

    @Test
    fun `検索中でエラーが発生するとメッセージを表示すること`() {
        val state = MainState(true, false, emptyList(), "error message")
        this.activity.render(state)

        Assert.assertEquals("error message", this.activity.messageTextView.text)
    }
}