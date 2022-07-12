package gsg.corp.movie_presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import gsg.corp.movie_domain.model.Dates
import gsg.corp.movie_domain.model.Movie
import gsg.corp.movie_domain.model.MoviesResponse
import gsg.corp.movie_domain.use_case.MovieUseCase
import kotlinx.coroutines.awaitAll

class MovieSource(useCase: MovieUseCase) : PagingSource<Int, Movie>() {
    private  val usecases=useCase

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
     
            val nextPage = params.key ?: 1
          
       
        return usecases.getMoviesUseCase(nextPage).onSuccess { }.map {
            LoadResult.Page(
                data = it.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (it.results.isEmpty()) null else it.page + 1
            )
            
        }.getOrNull()?:run{
            LoadResult.Page(
                data = emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = null
            )
        }
          
       
    }
}