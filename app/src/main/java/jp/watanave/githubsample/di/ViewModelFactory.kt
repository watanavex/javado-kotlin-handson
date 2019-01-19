package jp.watanave.githubsample.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

class ViewModelFactory @Inject constructor(
            private val creators: Map<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator: Provider<ViewModel> = creators[modelClass]
            ?: creators.entries
                .firstOrNull { (key, _) -> modelClass.isAssignableFrom(key) }
                ?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")

        try {
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun <T : ViewModel> get(activity: androidx.fragment.app.FragmentActivity, modelClass: KClass<T>) =
        ViewModelProviders.of(activity, this).get(modelClass.java)

    fun <T : ViewModel> get(fragment: androidx.fragment.app.Fragment, modelClass: KClass<T>) =
        ViewModelProviders.of(fragment, this).get(modelClass.java)
}