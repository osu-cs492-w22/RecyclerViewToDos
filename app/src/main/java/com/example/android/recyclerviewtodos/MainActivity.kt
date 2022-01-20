package com.example.android.recyclerviewtodos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoEntryET = findViewById<EditText>(R.id.et_todo_entry)
        val addToDoBtn = findViewById<Button>(R.id.btn_add_todo)
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.coordinator_layout)

        val todoListRV = findViewById<RecyclerView>(R.id.rv_todo_list)
        todoListRV.layoutManager = LinearLayoutManager(this)
        todoListRV.setHasFixedSize(true)

        val adapter = ToDoAdapter()
        todoListRV.adapter = adapter

        addToDoBtn.setOnClickListener {
            val newToDo = toDoEntryET.text.toString()
            if (!TextUtils.isEmpty(newToDo)) {
                adapter.addToDo(ToDo(newToDo, false))
                toDoEntryET.setText("")
                todoListRV.scrollToPosition(0)
            }
        }

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                val deletedTodo = adapter.deleteToDoAt(position)
                val snackbar = Snackbar.make(
                    coordinatorLayout,
                    "Deleted: ${deletedTodo.text}",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("UNDO") {
                    adapter.addToDo(deletedTodo, position)
                }
                snackbar.show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(todoListRV)
    }
}