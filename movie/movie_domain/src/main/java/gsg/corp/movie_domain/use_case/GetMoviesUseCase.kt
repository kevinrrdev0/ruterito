package gsg.corp.movie_domain.use_case

import gsg.corp.movie_domain.model.MoviesResponse
import gsg.corp.movie_domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page:Int):Result<MoviesResponse>{
        return repository.getMovies(page)
    }
}