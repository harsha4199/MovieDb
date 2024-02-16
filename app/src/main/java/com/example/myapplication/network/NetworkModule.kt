package com.example.myapplication.network

import com.example.myapplication.api.ApiService
import com.example.myapplication.repository.MovieDetailsRepository
import com.example.myapplication.repository.MovieDetailsRepositoryImpl
import com.example.myapplication.repository.MovieRepository
import com.example.myapplication.repository.MovieRepositoryImpl
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideMovieRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    fun provideMovieDetailsRepository(apiService: ApiService): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(apiService)
    }
}
