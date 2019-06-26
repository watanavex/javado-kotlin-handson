package jp.watanave.githubsample.data

import retrofit2.http.Query

class GithubApi(private val api: Api) {

    fun search(@Query("q") query: String): RepositoryResponse? {
        return this.api.search(query).execute().body()
    }

}