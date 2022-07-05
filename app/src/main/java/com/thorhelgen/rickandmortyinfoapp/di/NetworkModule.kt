package com.thorhelgen.rickandmortyinfoapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thorhelgen.rickandmortyinfoapp.data.remote.ApiService
import com.thorhelgen.rickandmortyinfoapp.data.remote.RemoteRepositoryImpl
import com.thorhelgen.rickandmortyinfoapp.domain.usecases.RemoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Converter.Factory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideMoshi(): Factory =
        MoshiConverterFactory.create(
            Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        )

    @Singleton
    @Provides
    fun provideRetrofit(moshiFactory: Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(moshiFactory)
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideRemoteRepository(apiService: ApiService): RemoteRepository =
        RemoteRepositoryImpl(apiService)
}