package jp.watanave.githubsample.practice6

import org.junit.Assert
import org.junit.Test

class PracticeTest {
    @Test
    fun `letやrunを試してみる`() {
        val view = View.createSample()
        val firstChildren = view.childView

        // 下のコードをletやrunを使って書き換えてみる
        if (firstChildren != null) {
            firstChildren.isHidden = true
        }
    }

    @Test
    fun `applyとalsoを試してみる1`() {
        // View.createSample()をapplyやalsoを使って書き換えてみる
        val view = View.createSample()
    }

    @Test
    fun `applyとalsoを試してみる2`() {
        // 下のテストが通るように、
        // hiddenFirstChildを書き換えてみる

        val parent = View()
        val child = View()
        val grandChild = View()
        parent.addView(child)
        child.addView(grandChild)

        parent.hiddenGrandChild()

        Assert.assertTrue(grandChild.isHidden)
    }
}