package jp.watanave.githubsample.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/repositories")
    fun search(@Query("q") query: String): Call<RepositoryResponse>

}

