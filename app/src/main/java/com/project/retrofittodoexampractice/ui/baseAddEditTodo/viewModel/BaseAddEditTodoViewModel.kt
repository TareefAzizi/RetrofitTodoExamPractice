package com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseAddEditTodoViewModel: ViewModel() {
    val finish: MutableSharedFlow<Unit> = MutableSharedFlow()
    val error: MutableSharedFlow<String> = MutableSharedFlow()
}