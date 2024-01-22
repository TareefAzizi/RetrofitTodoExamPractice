package com.project.retrofittodoexampractice.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.retrofittodoexampractice.data.model.Todo
import com.project.retrofittodoexampractice.databinding.ItemLayoutTodoBinding

class TodoAdapter(
    private var todos :List<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoItemViewHolder>() {
    var listener: Listener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = ItemLayoutTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoItemViewHolder(binding)
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val itemTodo = todos[position]
        holder.bind(itemTodo)
    }

    fun setTodos(todos: List<Todo>){
        this.todos = todos
        notifyDataSetChanged()
    }

    inner class TodoItemViewHolder(
        private val binding: ItemLayoutTodoBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.run {
                tvTitle.text = todo.title
                tvDesc.text = todo.desc

                cvTodo.setOnClickListener {
                    listener?.onClick(todo)
                }
                ivDelete.setOnClickListener {
                    listener?.onDelete(todo)
                }
            }
        }
    }


    interface Listener{
        fun onClick(todo: Todo)
        fun onDelete(todo: Todo)
    }
}