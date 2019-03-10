package jp.watanave.githubsample.practice5

import org.junit.Assert
import org.junit.Test
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses
import kotlin.reflect.jvm.javaType

class PracticeTest {

    @Test
    fun `クラスを継承してみる`() {
        // Employeeクラスを、Personを継承したクラスに変更する


        /* Javaで書くなら...

        class Employee extends Person {

            private int employeeId;

            public int getAge() {
                return employeeId;
            }

            Employee(String firstName, String lastName, int age, int employeeId) {
                super(firstName, lastName, age);
            }
        }

        */

        Assert.assertEquals(Employee::class.superclasses.first(), Person::class)
    }

    @Test
    fun `コンパニオンオブジェクトを使ってみる`() {
        // Employeeクラスのコンパニオンオブジェクトにdummyというプロパティを実装してみる
        // dummyプロパティはEmployeeクラスの適当なインスタンスを返す
        // 下のコードが実行できるように...

        /* Javaで書くなら...

        class Employee extends Person {

            static Employee getDummy() {
                return new Employee(0, "Duke", "Higuma", 3);
            }
        }

        */


        val dummy = Employee::class.companionObject!!::memberProperties.get().firstOrNull { it.name == "dummy" }
        Assert.assertNotNull(dummy)
        Assert.assertFalse(dummy is KMutableProperty1)
        Assert.assertEquals(dummy!!.returnType.javaType.typeName, "jp.watanave.githubsample.practice5.Employee")
    }

    @Test
    fun `シングルトンなクラスを実装してみる`() {
        // 下のクラスをシングルトンで実装してみる

        /*

        class Greeter {
            String greet() {
                return "Hello, World";
            }
        }

        */

        Assert.assertEquals(Greeter.greet(), "Hello, World")
    }

    @Test
    fun `dataクラスを試してみる`() {
        // このテストが通るようにSizeクラスを実装してみる

        val size1 = Size(width = 200, height = 200)
        val size2 = Size(width = 200, height = 200)
        Assert.assertEquals(size1, size2)
    }

    @Test
    fun `sealedクラスを試してみる`() {
        // 下のwhenのelseケースを書かずともコンパイルできるようにしてみる

        val api = Api()
        val result = api.call()
        val resultString = when (result) {
            is Success -> { result.value.toString() }
            is Failure -> { result.throwable.toString() }
        }
    }

    @Test
    fun `デリゲートを試してみる`() {
        // ISize、IPointをそれぞれ委譲したRectangleクラスを定義してみる

        Assert.assertTrue(Rectangle::class.superclasses.contains(ISize::class))
        Assert.assertTrue(Rectangle::class.superclasses.contains(IPoint::class))
    }

    @Test
    fun `カスタムプロパティを試してみる`() {
        // Personクラスにプロパティを追加してみる
        // String型、不変のfullNameプロパティ
        // カスタムゲッターを使い、firstNameとlastNameを空白スペースで結合して返してみる

        val person = Person("アンドリー", "ブレスラフ", 18)
        Assert.assertEquals(person.fullName, "アンドリー ブレスラフ")
    }
}