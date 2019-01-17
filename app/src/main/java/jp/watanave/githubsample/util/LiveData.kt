package jp.watanave.githubsample.util

import android.arch.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this)