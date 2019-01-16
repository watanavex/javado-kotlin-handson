package jp.watanave.githubsample

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import jp.watanave.githubsample.di.ApiModule
import jp.watanave.githubsample.di.UiModule
import javax.inject.Singleton

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerAppComponent.builder().create(this)

}

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, UiModule::class, ApiModule::class])
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<App>()
}