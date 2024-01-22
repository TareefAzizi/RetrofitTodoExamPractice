package com.project.retrofittodoexampractice.core.di


import com.project.retrofittodoexampractice.core.api.TodoApi
import com.project.retrofittodoexampractice.data.repo.TodoRepo
import com.project.retrofittodoexampractice.data.repo.TodoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTodoRepo(todoApi: TodoApi): TodoRepo {
        return TodoRepoImpl(todoApi)
    }
}