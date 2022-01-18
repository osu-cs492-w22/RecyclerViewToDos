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

        val toDoList = mutableListOf<String>()

        val toDoListTV = findViewById<TextView>(R.id.tv_todo_list)
        val toDoEntryET = findViewById<EditText>(R.id.et_todo_entry)
        val addToDoBtn = findViewById<Button>(R.id.btn_add_todo)

        addToDoBtn.setOnClickListener {
            val newToDo = toDoEntryET.text.toString()
            if (!TextUtils.isEmpty(newToDo)) {
                toDoList.add(0, newToDo)
                toDoListTV.text = toDoList.joinToString(separator = "\n\n☐  ", prefix = "☐  ")
                toDoEntryET.setText("")
            }
        }
    }
}