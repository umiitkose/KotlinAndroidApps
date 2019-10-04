package com.example.quiz.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.model.Question
import kotlinx.android.synthetic.main.activity_add_question.*

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var viewModel: AddQuestionViewModel
    private lateinit var answer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_question)

        viewModel = ViewModelProviders.of(this).get(AddQuestionViewModel::class.java)

        prepareSpinner()

        bt_save.setOnClickListener {
            val question = et_question.text.toString()
            val optionA = et_option_a.text.toString()
            val optionB = et_option_b.text.toString()
            val optionC = et_option_c.text.toString()
            val optionD = et_option_d.text.toString()

            viewModel.insert(
                Question(
                    question = question,
                    optionA = optionA,
                    optionB = optionB,
                    optionC = optionC,
                    optionD = optionD,
                    answer = if(answer== "A") optionA else if(answer == "B") optionB else if(answer == "C") optionC else optionD

                )
            )

        }
    }

    private fun prepareSpinner(){
        val optList = mutableListOf("A","B","C","D")
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,optList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                answer = p0?.getItemAtPosition(p2).toString()
            }

        }




    }
}
