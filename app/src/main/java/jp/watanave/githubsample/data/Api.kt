package jp.watanave.githubsample.data

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

interface Api {

    @GET("search/repositories")
    fun search(@Query("q") query: String): Call<RepositoryResponse>

}

class StubApi: Api {

    override fun search(@Query("q") query: String): Call<RepositoryResponse> {
        return object: Call<RepositoryResponse> {
            override fun enqueue(callback: Callback<RepositoryResponse>) {
            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun clone(): Call<RepositoryResponse> {
                return this
            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun cancel() {
                throw Exception()
            }

            override fun execute(): Response<RepositoryResponse> {
                val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
                val response = gson.fromJson(this@StubApi.dummuy, RepositoryResponse::class.java)
                Thread.sleep(1_000)
                return Response.success(response)
            }

            override fun request(): Request {
                throw Exception()
            }

        }
    }

    private val dummuy = """
        {
  "total_count": 50717,
  "incomplete_results": false,
  "items": [
    {
      "id": 3432266,
      "node_id": "MDEwOlJlcG9zaXRvcnkzNDMyMjY2",
      "name": "kotlin",
      "full_name": "JetBrains/kotlin",
      "private": false,
      "owner": {
        "login": "JetBrains",
        "id": 878437,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjg3ODQzNw==",
        "avatar_url": "https://avatars2.githubusercontent.com/u/878437?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/JetBrains",
        "html_url": "https://github.com/JetBrains",
        "followers_url": "https://api.github.com/users/JetBrains/followers",
        "following_url": "https://api.github.com/users/JetBrains/following{/other_user}",
        "gists_url": "https://api.github.com/users/JetBrains/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/JetBrains/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/JetBrains/subscriptions",
        "organizations_url": "https://api.github.com/users/JetBrains/orgs",
        "repos_url": "https://api.github.com/users/JetBrains/repos",
        "events_url": "https://api.github.com/users/JetBrains/events{/privacy}",
        "received_events_url": "https://api.github.com/users/JetBrains/received_events",
        "type": "Organization",
        "site_admin": false
      },
      "html_url": "https://github.com/JetBrains/kotlin",
      "description": "The Kotlin Programming Language",
      "fork": false,
      "url": "https://api.github.com/repos/JetBrains/kotlin",
      "forks_url": "https://api.github.com/repos/JetBrains/kotlin/forks",
      "keys_url": "https://api.github.com/repos/JetBrains/kotlin/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/JetBrains/kotlin/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/JetBrains/kotlin/teams",
      "hooks_url": "https://api.github.com/repos/JetBrains/kotlin/hooks",
      "issue_events_url": "https://api.github.com/repos/JetBrains/kotlin/issues/events{/number}",
      "events_url": "https://api.github.com/repos/JetBrains/kotlin/events",
      "assignees_url": "https://api.github.com/repos/JetBrains/kotlin/assignees{/user}",
      "branches_url": "https://api.github.com/repos/JetBrains/kotlin/branches{/branch}",
      "tags_url": "https://api.github.com/repos/JetBrains/kotlin/tags",
      "blobs_url": "https://api.github.com/repos/JetBrains/kotlin/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/JetBrains/kotlin/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/JetBrains/kotlin/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/JetBrains/kotlin/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/JetBrains/kotlin/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/JetBrains/kotlin/languages",
      "stargazers_url": "https://api.github.com/repos/JetBrains/kotlin/stargazers",
      "contributors_url": "https://api.github.com/repos/JetBrains/kotlin/contributors",
      "subscribers_url": "https://api.github.com/repos/JetBrains/kotlin/subscribers",
      "subscription_url": "https://api.github.com/repos/JetBrains/kotlin/subscription",
      "commits_url": "https://api.github.com/repos/JetBrains/kotlin/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/JetBrains/kotlin/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/JetBrains/kotlin/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/JetBrains/kotlin/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/JetBrains/kotlin/contents/{+path}",
      "compare_url": "https://api.github.com/repos/JetBrains/kotlin/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/JetBrains/kotlin/merges",
      "archive_url": "https://api.github.com/repos/JetBrains/kotlin/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/JetBrains/kotlin/downloads",
      "issues_url": "https://api.github.com/repos/JetBrains/kotlin/issues{/number}",
      "pulls_url": "https://api.github.com/repos/JetBrains/kotlin/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/JetBrains/kotlin/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/JetBrains/kotlin/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/JetBrains/kotlin/labels{/name}",
      "releases_url": "https://api.github.com/repos/JetBrains/kotlin/releases{/id}",
      "deployments_url": "https://api.github.com/repos/JetBrains/kotlin/deployments",
      "created_at": "2012-02-13T17:29:58Z",
      "updated_at": "2019-02-11T02:57:33Z",
      "pushed_at": "2019-02-10T23:20:46Z",
      "git_url": "git://github.com/JetBrains/kotlin.git",
      "ssh_url": "git@github.com:JetBrains/kotlin.git",
      "clone_url": "https://github.com/JetBrains/kotlin.git",
      "svn_url": "https://github.com/JetBrains/kotlin",
      "homepage": "http://kotlinlang.org/",
      "size": 518768,
      "stargazers_count": 26272,
      "watchers_count": 26272,
      "language": "Kotlin",
      "has_issues": false,
      "has_projects": false,
      "has_downloads": true,
      "has_wiki": false,
      "has_pages": true,
      "forks_count": 3025,
      "mirror_url": null,
      "archived": false,
      "open_issues_count": 77,
      "license": null,
      "forks": 3025,
      "open_issues": 77,
      "watchers": 26272,
      "default_branch": "master",
      "score": 163.6113
    },
    {
      "id": 91829561,
      "node_id": "MDEwOlJlcG9zaXRvcnk5MTgyOTU2MQ==",
      "name": "KotlinUdemy",
      "full_name": "hussien89aa/KotlinUdemy",
      "private": false,
      "owner": {
        "login": "hussien89aa",
        "id": 7304399,
        "node_id": "MDQ6VXNlcjczMDQzOTk=",
        "avatar_url": "https://avatars1.githubusercontent.com/u/7304399?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/hussien89aa",
        "html_url": "https://github.com/hussien89aa",
        "followers_url": "https://api.github.com/users/hussien89aa/followers",
        "following_url": "https://api.github.com/users/hussien89aa/following{/other_user}",
        "gists_url": "https://api.github.com/users/hussien89aa/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/hussien89aa/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/hussien89aa/subscriptions",
        "organizations_url": "https://api.github.com/users/hussien89aa/orgs",
        "repos_url": "https://api.github.com/users/hussien89aa/repos",
        "events_url": "https://api.github.com/users/hussien89aa/events{/privacy}",
        "received_events_url": "https://api.github.com/users/hussien89aa/received_events",
        "type": "User",
        "site_admin": false
      },
      "html_url": "https://github.com/hussien89aa/KotlinUdemy",
      "description": "Learn how to make online games, and apps for Android O, like Pokémon , twitter,Tic Tac Toe, and notepad using Kotlin",
      "fork": false,
      "url": "https://api.github.com/repos/hussien89aa/KotlinUdemy",
      "forks_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/forks",
      "keys_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/teams",
      "hooks_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/hooks",
      "issue_events_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/issues/events{/number}",
      "events_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/events",
      "assignees_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/assignees{/user}",
      "branches_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/branches{/branch}",
      "tags_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/tags",
      "blobs_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/languages",
      "stargazers_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/stargazers",
      "contributors_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/contributors",
      "subscribers_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/subscribers",
      "subscription_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/subscription",
      "commits_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/issues/comments{/number}",
      "contents_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/contents/{+path}",
      "compare_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/compare/{base}...{head}",
      "merges_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/merges",
      "archive_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/{archive_format}{/ref}",
      "downloads_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/downloads",
      "issues_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/issues{/number}",
      "pulls_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/pulls{/number}",
      "milestones_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/milestones{/number}",
      "notifications_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/notifications{?since,all,participating}",
      "labels_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/labels{/name}",
      "releases_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/releases{/id}",
      "deployments_url": "https://api.github.com/repos/hussien89aa/KotlinUdemy/deployments",
      "created_at": "2017-05-19T17:24:22Z",
      "updated_at": "2019-02-08T21:03:01Z",
      "pushed_at": "2018-10-31T08:10:34Z",
      "git_url": "git://github.com/hussien89aa/KotlinUdemy.git",
      "ssh_url": "git@github.com:hussien89aa/KotlinUdemy.git",
      "clone_url": "https://github.com/hussien89aa/KotlinUdemy.git",
      "svn_url": "https://github.com/hussien89aa/KotlinUdemy",
      "homepage": "https://www.udemy.com/the-complete-kotlin-developer-course/?couponCode=UDMEYGITHUB",
      "size": 1925,
      "stargazers_count": 581,
      "watchers_count": 581,
      "language": "Kotlin",
      "has_issues": true,
      "has_projects": true,
      "has_downloads": true,
      "has_wiki": true,
      "has_pages": false,
      "forks_count": 3278,
      "mirror_url": null,
      "archived": false,
      "open_issues_count": 7,
      "license": null,
      "forks": 3278,
      "open_issues": 7,
      "watchers": 581,
      "default_branch": "master",
      "score": 125.533066
    },
    {
      "id": 17946069,
      "node_id": "MDEwOlJlcG9zaXRvcnkxNzk0NjA2OQ==",
      "name": "kotlin-koans",
      "full_name": "Kotlin/kotlin-koans",
      "private": false,
      "owner": {
        "login": "Kotlin",
        "id": 1446536,
        "node_id": "MDEyOk9yZ2FuaXphdGlvbjE0NDY1MzY=",
        "avatar_url": "https://avatars3.githubusercontent.com/u/1446536?v=4",
        "gravatar_id": "",
        "url": "https://api.github.com/users/Kotlin",
        "html_url": "https://github.com/Kotlin",
        "followers_url": "https://api.github.com/users/Kotlin/followers",
        "following_url": "https://api.github.com/users/Kotlin/following{/other_user}",
        "gists_url": "https://api.github.com/users/Kotlin/gists{/gist_id}",
        "starred_url": "https://api.github.com/users/Kotlin/starred{/owner}{/repo}",
        "subscriptions_url": "https://api.github.com/users/Kotlin/subscriptions",
        "organizations_url": "https://api.github.com/users/Kotlin/orgs",
        "repos_url": "https://api.github.com/users/Kotlin/repos",
        "events_url": "https://api.github.com/users/Kotlin/events{/privacy}",
        "received_events_url": "https://api.github.com/users/Kotlin/received_events",
        "type": "Organization",
        "site_admin": false
      },
      "html_url": "https://github.com/Kotlin/kotlin-koans",
      "description": "Kotlin workshop",
      "fork": false,
      "url": "https://api.github.com/repos/Kotlin/kotlin-koans",
      "forks_url": "https://api.github.com/repos/Kotlin/kotlin-koans/forks",
      "keys_url": "https://api.github.com/repos/Kotlin/kotlin-koans/keys{/key_id}",
      "collaborators_url": "https://api.github.com/repos/Kotlin/kotlin-koans/collaborators{/collaborator}",
      "teams_url": "https://api.github.com/repos/Kotlin/kotlin-koans/teams",
      "hooks_url": "https://api.github.com/repos/Kotlin/kotlin-koans/hooks",
      "issue_events_url": "https://api.github.com/repos/Kotlin/kotlin-koans/issues/events{/number}",
      "events_url": "https://api.github.com/repos/Kotlin/kotlin-koans/events",
      "assignees_url": "https://api.github.com/repos/Kotlin/kotlin-koans/assignees{/user}",
      "branches_url": "https://api.github.com/repos/Kotlin/kotlin-koans/branches{/branch}",
      "tags_url": "https://api.github.com/repos/Kotlin/kotlin-koans/tags",
      "blobs_url": "https://api.github.com/repos/Kotlin/kotlin-koans/git/blobs{/sha}",
      "git_tags_url": "https://api.github.com/repos/Kotlin/kotlin-koans/git/tags{/sha}",
      "git_refs_url": "https://api.github.com/repos/Kotlin/kotlin-koans/git/refs{/sha}",
      "trees_url": "https://api.github.com/repos/Kotlin/kotlin-koans/git/trees{/sha}",
      "statuses_url": "https://api.github.com/repos/Kotlin/kotlin-koans/statuses/{sha}",
      "languages_url": "https://api.github.com/repos/Kotlin/kotlin-koans/languages",
      "stargazers_url": "https://api.github.com/repos/Kotlin/kotlin-koans/stargazers",
      "contributors_url": "https://api.github.com/repos/Kotlin/kotlin-koans/contributors",
      "subscribers_url": "https://api.github.com/repos/Kotlin/kotlin-koans/subscribers",
      "subscription_url": "https://api.github.com/repos/Kotlin/kotlin-koans/subscription",
      "commits_url": "https://api.github.com/repos/Kotlin/kotlin-koans/commits{/sha}",
      "git_commits_url": "https://api.github.com/repos/Kotlin/kotlin-koans/git/commits{/sha}",
      "comments_url": "https://api.github.com/repos/Kotlin/kotlin-koans/comments{/number}",
      "issue_comment_url": "https://api.github.com/repos/Kotlin/kotlin-koans/issues/comments{/number}"
    }
  ]
}
    """.trimIndent()
}