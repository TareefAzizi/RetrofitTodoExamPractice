package com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.data.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditTodoViewModel @Inject constructor(
    private val repo: TodoRepo
): BaseAddEditTodoViewModel() {
    private val _todo: MutableStateFlow<Todo> = MutableStateFlow(Todo(title = "", desc = ""))
    val todo: StateFlow<Todo> = _todo

    fun getTodoById(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            val res= repo.getTodoById(id)
            _todo.emit(res)
        }
    }

    fun updateTodo(id: String, todo: Todo){
        viewModelScope.launch(Dispatchers.IO){
            try {
                repo.updateTodo(id, todo)
                finish.emit(Unit)
            } catch (e: Exception){
                e.printStackTrace()
                error.emit("Update Todo Failed")
            }
        }
    }
}