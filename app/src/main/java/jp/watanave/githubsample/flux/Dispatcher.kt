package jp.watanave.githubsample.flux

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Dispatcher @Inject constructor() {

    private val actionProcessor = PublishProcessor.create<Action>()
    val actionObservable: Flowable<Action> = this.actionProcessor.hide()

    fun dispatch(action: Action) {
        this.actionProcessor.onNext(action)
    }

    inline fun <reified T: Action> actionPublisher(): Flowable<T> {
        return this.actionObservable.hide()
            .filter { it is T }
            .map { it as T }
    }

}