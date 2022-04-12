package gsg.corp.driver_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gsg.corp.driver_data.remote.DriverApi
import gsg.corp.driver_data.repository.DriverRepositoryImpl
import gsg.corp.driver_domain.repository.DriverRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DriverDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideDriverApi(client: OkHttpClient): DriverApi {
        return Retrofit.Builder()
            .baseUrl(DriverApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideDriverRepository(api: DriverApi):DriverRepository{
        return DriverRepositoryImpl(api)
    }

}