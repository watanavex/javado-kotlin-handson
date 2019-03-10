package jp.watanave.githubsample.practice3

import org.junit.Assert
import org.junit.Test

class PracticeTest {

    @Test
    fun `関数を定義してみる`() {
        // helloという名前の関数を定義してみる
        // 引数はString型、戻り値もString型
        // "Hello, {引数}"という文字列が戻り値になるような関数

        /* Javaで書くなら...

        String hello(String name) {
            return "Hello, " + name;
        }

        */

        val result = hello("Kotlin")
        Assert.assertEquals(result, "Hello, Kotlin")
    }

    @Test
    fun `デフォルト引数を定義してみる`() {
        // デフォルト引数を指定してみる
        // 上のhello関数のデフォルト引数をWorldにしてみる

        val result = hello()
        Assert.assertEquals(result, "Hello, World")
    }

    @Test
    fun `単一式関数を試してみる`() {
        // 単一式関数を書いてみる
        // 上のhello関数を単一式関数に書き直してみる

        val result = hello()
        Assert.assertEquals(result, "Hello, World")
    }

    @Test
    fun `ラムダ式を試してみる`() {
        /*** write here  ***/
        // 上で定義したhello関数と同じことラムダ式で実装してみる

        /* Javaで書くなら...

        Function<String, String> lambda = (name) -> {
            return "Hello, " + name;
        };

        */

        var lambda: ((String)->String)? = { "Hello, $it" }
        Assert.assertEquals(lambda?.invoke("Kotlin"), "Hello, Kotlin")
    }

    @Test
    fun `拡張関数を試してみる`() {
        // 拡張関数で遊ぶ
        // Int型を拡張して、自分の1.08倍の値を返す
        // includeTaxを定義してみましょう

        Assert.assertEquals(100.includeTax(), 108)
    }
}


fun notInline(i: Int, block: (Int)->Int) {
    block.invoke(i)
}
inline fun inline(i: Int, block: (Int)->Int) {
    block.invoke(i)
}