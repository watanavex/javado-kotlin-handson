package jp.watanave.githubsample.practice1

import java.lang.Exception

class Practice {

    fun getNum(): Int = 8
    fun getString(): String = "I love Kotlin!"

    fun checkSpeed(car: Car): CarStatus {
        val speed = car.getSpeed()
        if (speed < 0) {
            throw Exception()
        }
        else if (speed < 30) {
            return CarStatus.Slow
        }
        else if (speed < 60) {
            return CarStatus.Fast
        }
        else {
            return CarStatus.Caution
        }
    }

    fun checkSpeed2(car: Car): CarStatus {
        return when (car.getSpeed()) {
            in 0 until 30 -> CarStatus.Slow
            in 30 until 60 -> CarStatus.Fast
            in 60..Int.MAX_VALUE -> CarStatus.Caution
            else -> throw Exception()
        }
    }
}

interface Car {
    fun getSpeed(): Int
}
enum class CarStatus {
    Slow, Fast, Caution
}