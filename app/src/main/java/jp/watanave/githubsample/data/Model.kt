package jp.watanave.githubsample.data

data class RepositoryResponse(
    val items: List<Repository>
)

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: RepositoryOwner,
    val description: String
)

data class RepositoryOwner(
    val login: String,
    val id: Int,
    val avatarUrl: String
)