package com.example.movieapp.data.remote

import com.example.movieapp.model.detail.MovieDetailResponse
import com.example.movieapp.model.movie.MovieResponse
import com.example.movieapp.model.reviews.MovieReviewResponse
import com.example.movieapp.model.videos.MovieVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //Popular
    @GET("movie/popular")
    fun getPopularMovies(): Call<MovieResponse>

    //Toprated
    @GET("movie/top_rated")
    fun getTopRatedMovies(): Call<MovieResponse>

    //Details
    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") movieId: Int): Call<MovieDetailResponse>

    //Videos
    @GET("movie/{id}/videos")
    fun getMovieResponse(@Path("id") movieId: Int): Call<MovieVideoResponse>

    //Reviews
    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") movieId: Int): Call<MovieReviewResponse>

}