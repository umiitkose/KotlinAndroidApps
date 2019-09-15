package com.umiitkose.todolist.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.CalendarView
import android.widget.Toast
import com.umiitkose.todolist.R
import com.umiitkose.todolist.db.TaskRepository
import com.umiitkose.todolist.model.Task
import kotlinx.android.synthetic.main.activity_task.*
import java.util.*

class TaskActivity : AppCompatActivity() {

    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setSupportActionBar(task_toolbar)
        supportActionBar?.title = "Add Todo"

        taskRepository = TaskRepository(this)

        if(intent.extras != null){
            val task: Task = intent.extras!!.getSerializable(MainActivity.EXTRA_TASK) as Task
            task_name_et.setText((task.name))
            end_date_text.setText((task.date))

        }

        confirm_tab.setOnClickListener {
            if(intent.extras != null){
                val task: Task = intent.extras!!.getSerializable(MainActivity.EXTRA_TASK) as Task
                val rowId: Int = taskRepository.updateTask(Task(task.id, task_name_et.text.toString(),end_date_text.text.toString()))

                if(rowId > -1){
                    Toast.makeText(this,"Güncellendi",Toast.LENGTH_LONG).show()
                    }else {
                    Toast.makeText(this, "Güncelleme Başarısız", Toast.LENGTH_LONG).show()
                }

            }else{
                if(!TextUtils.isEmpty(task_name_et.text.toString())){
                    val date: String =
                        if(end_date_text.text == null || end_date_text.text == getString(R.string.end_date)) "No End Date"
                        else end_date_text.text.toString()

                    val row_id = taskRepository.insertTask(Task(name = task_name_et.text.toString(), date = date))

                    if(row_id > -1){
                        Toast.makeText(this,"Eklendi",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Eklenmedi",Toast.LENGTH_LONG).show()
                    }

                }else{
                    Toast.makeText(this,"Boş Geçilemez.",Toast.LENGTH_LONG).show()
                }}

        }

        end_date_layout.setOnClickListener { getDatePickerDialog() }
    }

    private fun getDatePickerDialog(){
        val c = Calendar.getInstance()
        val year: Int = c.get(Calendar.YEAR)
        val month: Int = c.get(Calendar.MONTH)
        val day: Int = c.get(Calendar.DAY_OF_MONTH)


        val dialog = DatePickerDialog(this,{view, year, month, dayOfMonth ->
            val endDate = "$dayOfMonth.$month.$year"
            end_date_text.text = endDate
        },year,month,day)

        dialog.datePicker.minDate = System.currentTimeMillis()
        dialog.show()
    }
}
