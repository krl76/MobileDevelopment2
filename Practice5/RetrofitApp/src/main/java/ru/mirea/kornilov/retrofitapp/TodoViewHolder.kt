package ru.mirea.kornilov.retrofitapp

import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TodoViewHolder(
    itemView: android.view.View
) : RecyclerView.ViewHolder(itemView) {
    private val image: ImageView = itemView.findViewById(R.id.imageTodo)
    private val title: TextView = itemView.findViewById(R.id.textViewTitle)
    private val completed: CheckBox = itemView.findViewById(R.id.checkBoxCompleted)

    fun bind(todo: Todo, onChecked: (Todo, Boolean) -> Unit) {
        title.text = todo.title
        completed.setOnCheckedChangeListener(null)
        completed.isChecked = todo.completed
        completed.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != todo.completed) {
                onChecked(todo, isChecked)
            }
        }
        Picasso.get()
            .load(imageUrl(todo.id))
            .resize(168, 168)
            .centerCrop()
            .placeholder(R.drawable.bg_placeholder)
            .error(R.drawable.bg_error_image)
            .into(image)
    }

    private fun imageUrl(id: Int): String {
        val photoId = PHOTO_IDS[id % PHOTO_IDS.size]
        return "https://picsum.photos/id/$photoId/200/200"
    }

    private companion object {
        val PHOTO_IDS = intArrayOf(29, 1015, 1016, 1018, 1019, 1011, 1036, 1039)
    }
}
