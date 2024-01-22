package com.project.retrofittodoexampractice.ui.baseAddEditTodo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.project.retrofittodoexampractice.R
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.databinding.FragmentBaseAddEditTodoBinding
import com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoFragment : BaseAddEditTodoFragment() {
    override lateinit var binding: FragmentBaseAddEditTodoBinding
    override val viewModel: AddTodoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {

            btnSubmit.text = "Add"
            btnSubmit.setOnClickListener {
                val title = etTitle.text.toString()
                val desc = etDesc.text.toString()

                viewModel.addTodo(Todo(title = title, desc = desc))
            }
        }
    }
}