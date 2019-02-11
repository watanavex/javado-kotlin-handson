package jp.watanave.githubsample.practice2

import org.junit.Assert
import org.junit.Test
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

class PracticeTest {

    @Test
    fun `クラスを定義してみる`() {
        // Personというクラスを定義してみましょう

        /* Javaで書くなら...

        class Person {
        }

        */
//        Assert.assertTrue(Person::class.java is Any)
    }

    @Test
    fun `クラスを定義してみる2`() {
        // Personというクラスにプロパティを定義してみましょう
        // 定義するプロパティは全部で3つ
        // String型、不変のfirstNameプロパティ
        // String型、不変のlastNameプロパティ
        // Int型、可変のageプロパティ

        // ★★★全てプライマリコンストラクタで定義してみましょう★★★

        /* Javaで書くなら...

        class Person {
            private String firstName;
            private String lastName;
            private int age;

            public String getFirstName() {
                return firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int mAge) {
                this.age = mAge;
            }
        }

        */

//        val firstName = Person::class.memberProperties.firstOrNull { it.name == "firstName" }
//        Assert.assertNotNull(firstName)
//        Assert.assertFalse(firstName is KMutableProperty1)
//        Assert.assertEquals(firstName!!.returnType.javaType.typeName, "java.lang.String")
//
//        val lastName = Person::class.memberProperties.firstOrNull { it.name == "lastName" }
//        Assert.assertNotNull(lastName)
//        Assert.assertFalse(lastName is KMutableProperty1)
//        Assert.assertEquals(lastName!!.returnType.javaType.typeName, "java.lang.String")
//
//        val age = Person::class.memberProperties.firstOrNull { it.name == "age" }
//        Assert.assertNotNull(age)
//        Assert.assertTrue(age is KMutableProperty1)
//        Assert.assertEquals(age!!.returnType.javaType.typeName, "int")
    }
}