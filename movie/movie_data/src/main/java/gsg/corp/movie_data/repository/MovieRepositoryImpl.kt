package gsg.corp.movie_data.repository

import gsg.corp.movie_data.remote.MovieApi
import gsg.corp.movie_domain.model.MoviesResponse
import gsg.corp.movie_domain.repository.MovieRepository
import java.lang.Exception

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {
    override suspend fun getMovies(page: Int): Result<MoviesResponse> {
        return try {
            val moviesDto= api.getMovies(page)
            Result.success(
                moviesDto
            )
        }catch (e: Exception){
            e.printStackTrace()
            Result.failure(e)
        }

    }
}