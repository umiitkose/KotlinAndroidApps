package com.example.quiz.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.R
import com.example.quiz.ui.add.AddQuestionActivity
import com.example.quiz.ui.quiz.QuizActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        bt_start_quiz.setOnClickListener {
        val intent = Intent(this,QuizActivity::class.java)
        startActivity(intent)
            finish()
        }

        bt_add_question.setOnClickListener {

            val intent = Intent(this,AddQuestionActivity::class.java)
            startActivity(intent)
        }
    }
}
