package jp.watanave.githubsample.util

import kotlinx.coroutines.CoroutineDispatcher

open class Dispatchers {

    open fun io(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
    open fun main(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main

}