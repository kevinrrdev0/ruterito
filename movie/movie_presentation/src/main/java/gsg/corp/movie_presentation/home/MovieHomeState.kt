package gsg.corp.movie_presentation.home

data class MovieHomeState(
    val listRoutes: List<MovieHomeUiState> = emptyList(),
    val isLoading: Boolean = false,
)
