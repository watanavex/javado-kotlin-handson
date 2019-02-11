package jp.watanave.githubsample.practice1

import java.lang.Exception

class Practice {

    fun getNum(): Int = 8
    fun getString(): String = "I love Kotlin!"

    fun checkSpeed(car: Car): CarStatus {
        throw Exception()
    }

    fun checkSpeed2(car: Car): CarStatus {
        throw Exception()
    }
}

interface Car {
    fun getSpeed(): Int
}
enum class CarStatus {
    Slow, Fast, Caution
}