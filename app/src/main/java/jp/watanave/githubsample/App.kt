package jp.watanave.githubsample

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import jp.watanave.githubsample.data.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    companion object {
        var instance: App? = null; private set
    }

    val api: Api by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

        retrofit.create(Api::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        App.instance = this
    }
}
