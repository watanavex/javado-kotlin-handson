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
        lateinit var instance: App; private set
    }

    val api: Api by lazy {
        when {
            BuildConfig.FLAVOR.startsWith("stub") -> {
                StubApi()
            }
            else -> {
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
        }

    }

    val githubSerachApi: GithubSerachApi by lazy {
        GithubSerachApi(this.api)
    }

    override fun onCreate() {
        super.onCreate()
        App.instance = this
    }

    class GithubSerachApi(private val api: jp.watanave.githubsample.data.Api) {

        fun search(query: String): List<Repository> {
            return this.api.search(query).execute().body()!!.items
        }

    }
}
