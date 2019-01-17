package jp.watanave.githubsample.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import jp.watanave.githubsample.data.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @Singleton
    @JvmStatic
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Singleton
    @JvmStatic
    @Provides
    fun retrofit(gson: Gson) = Retrofit
        .Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @JvmStatic
    @Provides
    fun api(retrofit: Retrofit) = retrofit
        .create(Api::class.java)
}