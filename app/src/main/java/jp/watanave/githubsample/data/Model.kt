package jp.watanave.githubsample.data

data class RepositoryResponse(
    val items: List<Repository>
)
data class Repository(
    val id: String,
    val name: String,
    val fullName: String,
    val htmlUrl: String
)

data class ReposittoryOwner(
    val login: String,
    val id: Int,
    val avatarUrl: Int
)