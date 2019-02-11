package jp.watanave.githubsample.practice5

open class Person(
    val firstName: String,
    val lastName: String,
    var age: Int
) {
}

interface ISize {
    val width: Int
    val height: Int
}

interface IPoint {
    val x: Int
    val y: Int
}

class Size(override val width: Int,
           override val height: Int): ISize

class Point(override val x: Int,
            override val y: Int): IPoint

class Rectangle()

open class Result
data class Success(val value: Any) : Result()
data class Failure(val throwable: Throwable) : Result()

open class Api {
    open fun call() : Result { return Success("kotlin") }
}

