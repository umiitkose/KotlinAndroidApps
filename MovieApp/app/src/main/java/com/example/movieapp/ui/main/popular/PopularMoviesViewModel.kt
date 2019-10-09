package com.example.movieapp.ui.main.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.movie.MovieResults
import com.example.movieapp.ui.main.MainRepository

class PopularMoviesViewModel: ViewModel() {

    private val repository: MainRepository by lazy { MainRepository() }

    fun getPopularMovies() : LiveData<List<MovieResults>>? = repository.getPopularMovies()

}