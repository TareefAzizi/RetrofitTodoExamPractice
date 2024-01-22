package com.project.retrofittodoexampractice.core.di

import com.project.retrofittodoexampractice.core.api.TodoApi
import com.project.retrofittodoexampractice.core.utils.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val logging = Logger()
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    val BASE_URL = "http://10.1.104.57:8080/"



    @Provides
    @Singleton
    fun provideTodoApi(): TodoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TodoApi::class.java)


}