package gsg.corp.movie_domain.repository

import gsg.corp.movie_domain.model.MoviesResponse

interface MovieRepository {

    suspend fun getMovies(page:Int):Result<MoviesResponse>
}