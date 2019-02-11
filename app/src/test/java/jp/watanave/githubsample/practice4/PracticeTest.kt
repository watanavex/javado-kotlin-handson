package jp.watanave.githubsample.practice4

import org.junit.Assert
import org.junit.Test

class PracticeTest {

    @Test
    fun `null許容型の変数を宣言してみる`() {
        // numという名前のInt型の変数をnullで初期化してみる

        /* Javaで書くなら...

        Integer num = null;

        */

//        Assert.assertNull(num)
    }

    @Test
    fun `スマートキャストを試してみる`() {
        val str = getString()
        // 変数numをif式を使ってスマートキャストし、
        // 下のコードがコンパイルできるようにしてみる

//        val length = str.length
    }

    @Test
    fun `安全呼び出しを試してみる`() {
        val greeter = getGreeter()

        // 下のコードを安全呼び出しに書き換えてみる
//        greeter.greet()
    }

    @Test
    fun `非null表明を試してみる`() {
        val greeter = getGreeter()

        // 下のコードを非null表明に書き換えてみる
//        greeter.greet()
    }

    @Test
    fun `安全キャストを試してみる`() {
        val any = getAny()

        // 変数anyをGreeterにキャストして、greet関数を呼んでみる
        /* Javaで書くなら...

        if (any instanceOf Greeter) {
            ((Greeter)any).greet()
        }

        */
    }

    @Test
    fun `エルビス演算子を使ってみる`() {
        // 関数helloをエルビス演算子を使った実装に書き換えてみる
        val str = hello(null)
        Assert.assertEquals(str, "Hello, World")
    }
}