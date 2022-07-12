package gsg.corp.movie_presentation.home

data class MovieHomeUiState(
    val id: Int,
    val title: String,
    val imageUrl: String?,//https://image.tmdb.org/t/p/original/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg
    val popularity: Double
)
