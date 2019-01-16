package jp.watanave.githubsample.flux

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class Dispatcher {

    private val actionSubject = PublishSubject.create<Action>()
    val actionObservable: Observable<Action> = this.actionSubject.hide()

    fun dispatch(action: Action) {
        this.actionSubject.onNext(action)
    }

    inline fun <reified T: Action> actionPublisher(): Observable<T> {
        return this.actionObservable.hide()
            .filter { it is T }
            .map { it as T }
    }

}