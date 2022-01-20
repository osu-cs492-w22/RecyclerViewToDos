package com.example.android.recyclerviewtodos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    val toDos: MutableList<ToDo> = mutableListOf()

    override fun getItemCount() = this.toDos.size

    fun addToDo(toDo: ToDo, position: Int = 0) {
        this.toDos.add(position, toDo)
        this.notifyItemInserted(position)
    }

    fun deleteToDoAt(position: Int): ToDo {
        val toDo = this.toDos.removeAt(position)
        this.notifyItemRemoved(position)
        return toDo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.toDos[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBox: CheckBox = view.findViewById(R.id.todo_checkbox)
        private val todoTextTV: TextView = view.findViewById(R.id.tv_todo_text)

        init {
            checkBox.setOnCheckedChangeListener { button, isChecked ->
                toDos[absoluteAdapterPosition].completed = isChecked
            }
        }

        fun bind(toDo: ToDo) {
            this.checkBox.isChecked = toDo.completed
            this.todoTextTV.text = toDo.text
        }
    }
}