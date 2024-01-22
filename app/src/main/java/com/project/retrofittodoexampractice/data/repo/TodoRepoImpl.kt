package com.project.retrofittodoexampractice.data.repo

import com.project.retrofittodoexampractice.core.api.TodoApi
import com.project.retrofittodoexampractice.data.model.Todo

class TodoRepoImpl(
    private val api: TodoApi
):TodoRepo {
    override suspend fun getAllTodos(): List<Todo> {
        return api.getTodos()
    }

    override suspend fun getTodoById(id: String): Todo {
        return api.getTodoById(id)
    }

    override suspend fun addTodo(todo: Todo) {
        api.addTodo(todo)
    }

    override suspend fun updateTodo(id: String, todo: Todo) {
        api.updateTodo(id, todo)
    }

    override suspend fun deleteTodo(id: String) {
        api.deleteTodo(id)
    }
}