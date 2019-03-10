package jp.watanave.githubsample.practice5

open class Person(
    val firstName: String,
    val lastName: String,
    var age: Int
) {
    val fullName: String
        get() = "${this.firstName} ${this.lastName}"
}

class Employee(private val employeeId: Int,
               firstName: String,
               lastName: String,
               age: Int): Person(firstName, lastName, age) {

    companion object {
        val dummy: Employee
            get() = Employee(0, "Duke", "Higuma", 3)
    }
}

object Greeter {
    fun greet() = "Hello, World"
}

interface ISize {
    val width: Int
    val height: Int
}

interface IPoint {
    val x: Int
    val y: Int
}

data class Size(override val width: Int,
           override val height: Int): ISize

class Point(override val x: Int,
            override val y: Int): IPoint

class Rectangle(val size: Size, val point: Point): ISize by size, IPoint by point

sealed class Result
data class Success(val value: Any) : Result()
data class Failure(val throwable: Throwable) : Result()

open class Api {
    open fun call() : Result { return Success("kotlin") }
}

