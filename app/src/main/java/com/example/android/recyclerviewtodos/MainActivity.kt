package com.example.android.recyclerviewtodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoList = mutableListOf<String>()

        val todoListTV = findViewById<TextView>(R.id.tv_todo_list)
        val todoEntryET = findViewById<EditText>(R.id.et_todo_entry)
        val addTodoBtn = findViewById<Button>(R.id.btn_add_todo)

        addTodoBtn.setOnClickListener {
            val newTodo = todoEntryET.text.toString()
            if (!TextUtils.isEmpty(newTodo)) {
                todoList.add(0, newTodo)
                todoListTV.text = todoList.joinToString(separator = "\n\n☐  ", prefix = "☐  ")
                todoEntryET.setText("")
            }
        }
    }
}