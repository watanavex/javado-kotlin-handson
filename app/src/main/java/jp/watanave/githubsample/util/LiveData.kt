package jp.watanave.githubsample.util

import androidx.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this)