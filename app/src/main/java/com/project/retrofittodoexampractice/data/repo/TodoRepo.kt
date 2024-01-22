package com.project.retrofittodoexampractice.data.repo

import com.project.retrofittodoexampractice.data.model.Todo

interface TodoRepo {
    suspend fun getAllTodos(): List<Todo>
    suspend fun getTodoById(id:String): Todo
    suspend fun addTodo(todo: Todo)
    suspend fun updateTodo(id: String, todo: Todo)
    suspend fun deleteTodo(id: String)
}