package ru.mirea.kornilov.retrofitapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val status = findViewById<TextView>(R.id.textStatus)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        todoAdapter = TodoAdapter { todo, checked -> updateTodo(todo, checked) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = todoAdapter

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        apiService.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                if (response.isSuccessful && response.body() != null) {
                    todoAdapter.setItems(response.body()!!)
                } else {
                    status.visibility = android.view.View.VISIBLE
                    status.text = getString(R.string.load_error)
                    Log.e(TAG, "onResponse: " + response.code())
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                status.visibility = android.view.View.VISIBLE
                status.text = getString(R.string.load_error)
                Log.e(TAG, "onFailure: " + t.message)
            }
        })
    }

    private fun updateTodo(todo: Todo, checked: Boolean) {
        val updated = todo.copy(completed = checked)
        apiService.updateTodo(todo.id, updated).enqueue(object : Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    todoAdapter.replace(updated)
                } else {
                    todoAdapter.replace(todo)
                    Toast.makeText(this@MainActivity, R.string.update_error, Toast.LENGTH_SHORT).show()
                    Log.e(TAG, "update onResponse: " + response.code())
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                todoAdapter.replace(todo)
                Toast.makeText(this@MainActivity, R.string.update_error, Toast.LENGTH_SHORT).show()
                Log.e(TAG, "update onFailure: " + t.message)
            }
        })
    }

    private companion object {
        const val TAG = "MainActivity"
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}
