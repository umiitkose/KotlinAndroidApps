package com.example.movieapp.data.remote

import com.example.movieapp.util.Constans
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", Constans.API_KEY)
            .build()

        val request= originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}