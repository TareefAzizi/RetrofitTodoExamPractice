package com.project.retrofittodoexampractice.ui.baseAddEditTodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.project.retrofittodoexampractice.R
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.databinding.FragmentBaseAddEditTodoBinding
import com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel.EditTodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditTodoFragment : BaseAddEditTodoFragment() {
    override lateinit var binding: FragmentBaseAddEditTodoBinding
    override val viewModel: EditTodoViewModel by viewModels()
    private val navArgs: EditTodoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTodoById(navArgs.todoId)

        binding.run {
            btnSubmit.text = "Update"

            btnSubmit.setOnClickListener {
                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()

                viewModel.updateTodo(navArgs.todoId, Todo(title = title, desc = desc))
            }
        }

        lifecycleScope.launch {
            viewModel.todo.collect{
                binding.run {
                    etTitle.setText(it.title)
                    etDesc.setText(it.desc)
                }
            }
        }
    }
}