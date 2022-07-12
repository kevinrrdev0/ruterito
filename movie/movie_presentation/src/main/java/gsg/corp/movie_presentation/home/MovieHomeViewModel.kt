package gsg.corp.movie_presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import gsg.corp.core.domain.preferences.Preferences
import gsg.corp.movie_domain.model.Movie
import gsg.corp.movie_domain.use_case.GetMoviesUseCase
import gsg.corp.movie_domain.use_case.MovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val pref: Preferences,
    private val useCases: MovieUseCase
) : ViewModel() {
    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(useCases)
    }.flow.cachedIn(viewModelScope)

    private fun getMovies(page:Int){

    }

}