package com.example.vote_01.Server

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.GsonBuilder

import com.google.gson.Gson

@Module
@InstallIn(SingletonComponent::class)
object ServerModule {
    @Provides
    @Singleton
    fun provideApi(): ServerApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ServerApi.BASE_URL)
            .build()
            .create(ServerApi::class.java)
    }

}
