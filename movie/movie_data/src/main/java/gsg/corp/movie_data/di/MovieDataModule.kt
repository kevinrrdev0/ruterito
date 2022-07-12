package gsg.corp.movie_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.movie_data.remote.MovieApi
import gsg.corp.movie_data.repository.MovieRepositoryImpl
import gsg.corp.movie_domain.repository.MovieRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDataModule {



    @Provides
    @Singleton
    fun provideDriverApi(client: OkHttpClient): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi):MovieRepository{
        return MovieRepositoryImpl(api)

    }
}