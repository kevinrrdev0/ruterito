package gsg.corp.movie_data.remote

import gsg.corp.movie_data.remote.dto.MovieDto
import gsg.corp.movie_domain.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(MOVIE_HOME_ROUTE)
    suspend fun getMovies(@Query("page") page: Int, @Query("api_key") api_key:String = "f46b58478f489737ad5a4651a4b25079"):MoviesResponse

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
    }

}