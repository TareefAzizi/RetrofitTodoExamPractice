package com.project.retrofittodoexampractice.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.retrofittodoexampractice.R
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.databinding.FragmentHomeBinding
import com.project.retrofittodoexampractice.ui.adapter.TodoAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        setupAdapter()

        binding.run {
            btnAdd.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToAddTodoFragment()
                navController.navigate(action)
            }

            setFragmentResultListener("from_add_edit_todo"){_, result ->
                val refresh = result.getBoolean("refresh")
                if (refresh){
                    viewModel.refresh()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.todos.collect{
                adapter.setTodos(it)
            }
        }

        lifecycleScope.launch{
            viewModel.finish.collect{
                viewModel.refresh()
            }
        }
    }

    private fun setupAdapter(){
        adapter = TodoAdapter(emptyList())
        adapter.listener = object: TodoAdapter.Listener{
            override fun onClick(todo: Todo) {
                val action = HomeFragmentDirections.actionHomeFragmentToEditTodoFragment(todo._id!!)
                navController.navigate(action)
            }

            override fun onDelete(todo: Todo) {
                viewModel.deleteTodo(todo._id!!)
            }

        }

        binding.rvTodo.adapter = adapter
        binding.rvTodo.layoutManager = LinearLayoutManager(requireContext())
    }

}