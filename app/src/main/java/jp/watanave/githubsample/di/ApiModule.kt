package jp.watanave.githubsample.di

import dagger.Module
import dagger.Provides
import jp.watanave.githubsample.data.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @JvmStatic
    @Provides
    fun retrofit() = Retrofit
        .Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @JvmStatic
    @Provides
    fun service(retrofit: Retrofit) = retrofit
        .create(Api::class.java)
}