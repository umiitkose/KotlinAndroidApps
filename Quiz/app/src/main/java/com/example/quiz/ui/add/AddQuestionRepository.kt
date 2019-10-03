package com.example.quiz.ui.add

import android.content.Context
import android.os.AsyncTask
import com.example.quiz.data.QuestionDB
import com.example.quiz.data.QuestionDao
import com.example.quiz.model.Question

class AddQuestionRepository(context: Context){

    private val db by lazy { QuestionDB.getInstance(context) }
    private val dao: QuestionDao by lazy { db.questionDao() }

    fun insertQuestion(question: Question){
        InsertAsyncTask(dao).execute(question)
    }

    private class InsertAsyncTask(val dao: QuestionDao): AsyncTask<Question, Void, Void>(){
        override fun doInBackground(vararg p0: Question?): Void? {
            dao.addQuestion(p0[0]!!)
            return null
        }


    }
}