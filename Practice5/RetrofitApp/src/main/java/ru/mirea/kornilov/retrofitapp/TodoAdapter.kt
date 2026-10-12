package ru.mirea.kornilov.retrofitapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val onChecked: (Todo, Boolean) -> Unit
) : RecyclerView.Adapter<TodoViewHolder>() {

    private val todos = mutableListOf<Todo>()

    fun setItems(items: List<Todo>) {
        todos.clear()
        todos.addAll(items)
        notifyDataSetChanged()
    }

    fun replace(updated: Todo) {
        val index = todos.indexOfFirst { it.id == updated.id }
        if (index >= 0) {
            todos[index] = updated
            notifyItemChanged(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position], onChecked)
    }

    override fun getItemCount(): Int = todos.size
}
