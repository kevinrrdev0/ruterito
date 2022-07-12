package gsg.corp.movie_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import gsg.corp.movie_domain.repository.MovieRepository
import gsg.corp.movie_domain.use_case.GetMoviesUseCase
import gsg.corp.movie_domain.use_case.MovieUseCase

@Module
@InstallIn(ViewModelComponent::class)
object MovieDomainModule {

    @ViewModelScoped
    @Provides
    fun provideMovieUseCases(
        repository: MovieRepository
    ):MovieUseCase{
        return MovieUseCase(
            getMoviesUseCase = GetMoviesUseCase(repository)
        )
    }
}