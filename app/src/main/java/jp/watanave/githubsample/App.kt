package jp.watanave.githubsample

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import jp.watanave.githubsample.data.Api
import jp.watanave.githubsample.data.Repository
import jp.watanave.githubsample.data.StubApi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        var instance: App? = null; private set
    }

    override fun onCreate() {
        super.onCreate()
        App.instance = this
    }
}
