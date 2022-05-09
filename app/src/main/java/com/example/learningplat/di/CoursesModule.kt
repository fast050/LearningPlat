package com.example.learningplat.di

import com.example.learningplat.data.network.CoursesApi
import com.example.learningplat.data.network.CoursesApiService
import com.example.learningplat.repository.CoursesRepository
import com.example.learningplat.repository.DefaultCoursesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoursesModule {

    @Provides
    @Singleton
    fun provideCoursesService(): CoursesApiService =
        Retrofit.Builder().baseUrl(CoursesApi.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoursesApiService::class.java)

    @Provides
    @Singleton
    fun provideCoursesRepository(api:CoursesApiService):CoursesRepository
    =DefaultCoursesRepository(api)


}