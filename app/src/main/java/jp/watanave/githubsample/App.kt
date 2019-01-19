package jp.watanave.githubsample

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import jp.watanave.githubsample.di.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().create(this)
}
