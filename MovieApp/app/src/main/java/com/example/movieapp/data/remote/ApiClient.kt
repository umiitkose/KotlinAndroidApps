package com.example.movieapp.data.remote

import com.example.movieapp.util.Constans
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getApiService(): ApiService{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())

        return client.build()

    }

}