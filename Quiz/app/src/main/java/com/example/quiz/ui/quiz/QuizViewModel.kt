package com.example.quiz.ui.quiz

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quiz.model.Question

class QuizViewModel(application: Application): AndroidViewModel(application) {

    private val repository: QuizRepository by lazy {
        QuizRepository(application)
    }

    val allQuestions: LiveData<List<Question>> by lazy {
        repository.getAllQuestions()
    }
}