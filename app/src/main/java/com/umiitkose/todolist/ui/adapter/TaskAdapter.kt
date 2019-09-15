package com.umiitkose.todolist.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.umiitkose.todolist.R
import com.umiitkose.todolist.model.Task


class TaskAdapter(var context: Context, var taskList: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private lateinit var onTaskCompleteListener: OnTaskCompleteListener
    private lateinit var onTaskEditListener: OnTaskEditListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = taskList[position].name
        holder.taskDate.text = taskList[position].date
        holder.completeCheckBox.isChecked = false

        holder.completeCheckBox.setOnClickListener {
            onTaskCompleteListener.let {
                it.onTaskComlete(taskList[position].id)
            }
        }

        holder.itemView.setOnCreateContextMenuListener{menu, v, menuInfo ->
            menu.add("Edit").setOnMenuItemClickListener {
                onTaskEditListener.let {
                    it.onEditTask(taskList[position])
                }
                return@setOnMenuItemClickListener true

            }
        }
    }

    fun updateList(newList: ArrayList<Task>){
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val taskName: TextView = view.findViewById<TextView>(R.id.item_task_name)
        val taskDate: TextView = view.findViewById<TextView>(R.id.item_date)
        val completeCheckBox: CheckBox= view.findViewById<CheckBox>(R.id.item_complete)
    }

    fun setOnTaskCompleteListener(onTaskCompleteListener: OnTaskCompleteListener){
        this.onTaskCompleteListener = onTaskCompleteListener
    }

    fun setOnTaskEditListener(onTaskEditListener: OnTaskEditListener){
        this.onTaskEditListener = onTaskEditListener
    }

    interface OnTaskCompleteListener {
        fun onTaskComlete(taskId: Int)

    }
    interface OnTaskEditListener {
        fun onEditTask(task: Task)

    }

}
