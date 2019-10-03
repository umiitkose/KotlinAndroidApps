package com.example.quiz.ui.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quiz.R
import com.example.quiz.model.Question
import com.example.quiz.ui.result.ResultActivity
import com.example.quiz.util.Constants
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var viewModel: QuizViewModel
    private var questionList: List<Question> = arrayListOf()
    private var qIndex: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        viewModel.allQuestions.observe(this, Observer {

            if(it.isNotEmpty()){
                questionList = it
                setViews()

                bt_next.setOnClickListener {

                    val answer = findViewById<Chip>(chip_grup.checkedChipId)
                    chip_grup.clearCheck()
                    checkAnswer(answer)
                    qIndex++
                    if(qIndex < questionList.size) setViews()
                    else{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.EXTRA_RESULT,score)
                        intent.putExtra(Constants.EXTRA_LIST_SIZE,questionList.size)
                        startActivity(intent)
                        finish()
                    }

                }
            }


        })
    }

    private fun setViews(){
        tv_question.text = questionList[qIndex].question
        chip_option_a.text = questionList[qIndex].optionA
        chip_option_b.text = questionList[qIndex].optionB
        chip_option_c.text = questionList[qIndex].optionC
        chip_option_d.text = questionList[qIndex].optionD

    }

    private fun checkAnswer(answer: Chip){
        if(questionList[qIndex].answer == answer.text){
            Toast.makeText(this, "Doğru Cevap",Toast.LENGTH_LONG).show()
            score++
        }else{
            Toast.makeText(this, "Yanlış Cevap\n Cevap = ${questionList[qIndex].answer}",Toast.LENGTH_LONG).show()
        }
    }

}
