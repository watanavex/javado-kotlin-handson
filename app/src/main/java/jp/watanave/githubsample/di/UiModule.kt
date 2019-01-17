package jp.watanave.githubsample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.watanave.githubsample.ui.main.MainActivity
import jp.watanave.githubsample.ui.main.MainModule
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Module
abstract class UiModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
