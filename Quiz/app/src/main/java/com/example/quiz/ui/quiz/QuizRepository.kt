package com.example.quiz.ui.quiz

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.quiz.data.QuestionDB
import com.example.quiz.data.QuestionDao
import com.example.quiz.model.Question

class QuizRepository(context: Context) {
    private val db by lazy {
        QuestionDB.getInstance(context)
    }
    private val dao: QuestionDao by lazy{
        db.questionDao()
    }

    fun getAllQuestions(): LiveData<List<Question>> =
        dao.getQuestions()
}