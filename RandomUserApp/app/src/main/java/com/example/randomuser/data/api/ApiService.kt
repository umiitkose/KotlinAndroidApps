package com.example.randomuser.data.api

import com.example.randomuser.model.Results
import com.example.randomuser.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getUsers(@Query("results") results: Int): Call<UserResponse>

}