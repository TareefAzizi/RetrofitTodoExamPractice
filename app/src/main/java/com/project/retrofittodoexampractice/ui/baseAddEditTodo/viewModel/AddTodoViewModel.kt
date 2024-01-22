package com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.data.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repo: TodoRepo
) : BaseAddEditTodoViewModel() {

    fun addTodo(todo: Todo){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addTodo(todo)
                finish.emit(Unit)
            } catch (e: Exception){
                e.printStackTrace()
                error.emit("Add Todo Failes")
            }
        }
    }
}