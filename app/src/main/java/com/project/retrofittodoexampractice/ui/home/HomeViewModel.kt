package com.project.retrofittodoexampractice.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.data.repo.TodoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: TodoRepo
): ViewModel() {

    // MutableStateFlow to hold a list of Todo items
    private val _todos: MutableStateFlow<List<Todo>> = MutableStateFlow(emptyList())
    // Exposes an immutable StateFlow for observing the list of Todo items
    val todos: StateFlow<List<Todo>> = _todos

    // A MutableSharedFlow used to signal that an operation has finished
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()

    // Constructor initializes the ViewModel and triggers loading of todos
    init {
        getAllTodos()
    }

    // Function to fetch all Todo items from the repository
    private fun getAllTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getAllTodos()
            // Emit the list of todos to the _todos MutableStateFlow
            _todos.emit(result)
        }
    }

    // Function to delete a Todo item by its ID
    fun deleteTodo(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Call the repository to delete the Todo item
                repo.deleteTodo(id)
                // Emit a signal to indicate that the operation is finished
                finish.emit(Unit)
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    // Function to refresh the list of Todo items
    fun refresh(){
        getAllTodos()
    }
}
