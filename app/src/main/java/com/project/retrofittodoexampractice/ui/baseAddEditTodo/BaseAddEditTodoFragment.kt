package com.project.retrofittodoexampractice.ui.baseAddEditTodo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.project.retrofittodoexampractice.R
import com.project.retrofittodoexampractice.databinding.FragmentBaseAddEditTodoBinding
import com.project.retrofittodoexampractice.ui.baseAddEditTodo.viewModel.BaseAddEditTodoViewModel
import kotlinx.coroutines.launch

abstract class BaseAddEditTodoFragment : Fragment() {
    abstract var binding: FragmentBaseAddEditTodoBinding
    private lateinit var navController: NavController
    abstract val viewModel: BaseAddEditTodoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBaseAddEditTodoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)

        // Using a coroutine from the lifecycleScope to collect events from the viewModel's 'finish' flow
        lifecycleScope.launch {
            viewModel.finish.collect {
                // When an event is collected, create a Bundle to pass data
                val bundle = Bundle()
                bundle.putBoolean("refresh", true) // We put a boolean value 'refresh' into the Bundle

                // Set a FragmentResult with the Bundle, which can be received by other fragments
                setFragmentResult("from_add_edit_todo", bundle)

                // Navigate back in the navigation stack using the NavController
                navController.popBackStack()
            }
        }

        // Another coroutine for collecting events from the viewModel's 'error' flow
        lifecycleScope.launch {
            viewModel.error.collect {
                // Display a Toast message with the error message received from the viewModel
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }


}