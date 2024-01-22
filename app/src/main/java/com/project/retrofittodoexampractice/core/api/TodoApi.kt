package com.project.retrofittodoexampractice.core.api

import com.project.retrofittodoexampractice.data.model.Todo
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApi {
    @GET("todos/tareef")
    suspend fun getTodos(): List<Todo>

    @GET("todos/tareef/{id}")
    suspend fun getTodoById(@Path("id") id:String): Todo

    @POST("/todos")
    suspend fun  addTodo(@Body todo:Todo)

    @PUT("/todos/tareef/{id}")
    suspend fun updateTodo(@Path("id") id:String, @Body todo: Todo)

    @DELETE("/todos/tareef/{id}")
    suspend fun deleteTodo(@Path("id") id:String)
}