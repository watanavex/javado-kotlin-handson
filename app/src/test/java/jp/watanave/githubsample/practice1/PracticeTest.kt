package jp.watanave.githubsample.practice1

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class PracticeTest {

    @Test
    fun `変数を宣言してみる`() {
        // numという名前のInt型の変数を宣言してみる
        // numは代入できない宣言にしてみる

        /* Javaで書くなら...

        final int num = 3;

        */

//        Assert.assertEquals(num, 3)
    }

    @Test
    fun `変数を宣言してみる2`() {
        // numという名前のInt型の変数を宣言してみる
        // numは再代入できる宣言にしましょう

        /* Javaで書くなら...

        int num = 3;

        */

//        num = 6
//        Assert.assertEquals(num, 6)
    }

    @Test
    fun `型推論を試してみる`() {
//        val intValue = // Intのリテラルを書く
//        val longValue = // Longのリテラルを書く
//        val stringValue = // Stringのリテラルを書く
//
//        Assert.assertTrue(intValue is Int)
//        Assert.assertTrue(longValue is Long)
//        Assert.assertTrue(stringValue is String)
    }

    @Test
    fun `型推論を試してみる2`() {
        val practice = Practice()

        // Practice#getNumの戻り値をnumという変数に代入してみる
        // Practice#getStringの戻り値をstrという変数に代入してみる

        /* Javaで書くなら...

        Practice practice = new Practice();
        int num = practice.getNum();
        String str = practice.getString();

        */

//        Assert.assertTrue(num is Int)
//        Assert.assertTrue(str is String)
    }

    @Test
    fun `条件分岐を書いてみる`() {
        val car = mockk<Car>()
        val practice = Practice()
        // Practice#checkSpeedを実装してみましょう (ifを使ってみましょう)

        /* Javaで書くなら...

        CarStatus checkSpeed(Car car) {
            if (car.getSpeed() < 0) {
                throw new Exception();
            }
            else if (car.getSpeed() < 30) {
                return CarStatus.Slow;
            }
            else if (car.getSpeed() < 60) {
                return CarStatus.Fast;
            }
            else {
                return CarStatus.Caution;
            }
        }

        */

        // car.getSpeed()が29だったら？
        every { car.getSpeed() } returns 29
        Assert.assertEquals(practice.checkSpeed(car), CarStatus.Slow)

        // car.getSpeed()が59だったら？
        every { car.getSpeed() } returns 59
        Assert.assertEquals(practice.checkSpeed(car), CarStatus.Fast)

        // car.getSpeed()が100だったら？
        every { car.getSpeed() } returns 100
        Assert.assertEquals(practice.checkSpeed(car), CarStatus.Caution)
    }

    @Test
    fun `条件分岐を書いてみる2`() {
        val car = mockk<Car>()
        val practice = Practice()
        // Practice#checkSpeed2を実装してみましょう (今度はWhenを使ってみましょう)
        // ヒント: https://kotlinlang.org/docs/reference/control-flow.html

        // car.getSpeed()が29だったら？
        every { car.getSpeed() } returns 29
        Assert.assertEquals(practice.checkSpeed2(car), CarStatus.Slow)

        // car.getSpeed()が59だったら？
        every { car.getSpeed() } returns 59
        Assert.assertEquals(practice.checkSpeed2(car), CarStatus.Fast)

        // car.getSpeed()が100だったら？
        every { car.getSpeed() } returns 100
        Assert.assertEquals(practice.checkSpeed2(car), CarStatus.Caution)
    }

    @Test
    fun `コレクションを扱ってみる`() {
        // (0, 2, 4)を要素に持つIntのListを宣言してみる

        /* Javaで書くなら...

        List<Integer> list = Arrays.asList(0, 2, 4);

        */

//        Assert.assertEquals(list[0], 0)
//        Assert.assertEquals(list[1], 2)
//        Assert.assertEquals(list[2], 4)
    }

    @Test
    fun `コレクションを扱ってみる2`() {
        // 変更可能なIntのListを宣言してみる
        // 要素0, 2, 4を追加してみる

        /* Javaで書くなら...

        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(2);
        list.add(4);

        */

//        Assert.assertEquals(list[0], 0)
//        Assert.assertEquals(list[1], 2)
//        Assert.assertEquals(list[2], 4)
    }
}