package id.ronnysugianto.themoviedb.data

data class MovieReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<Review>,
    val total_pages: Int,
    val total_results: Int
)

data class Review(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)

data class AuthorDetails(
    val name: String,
    val username: String,
    val avatar_path: String?,
    val rating: Double?
)
