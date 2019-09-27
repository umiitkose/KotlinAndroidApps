package com.example.randomuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.randomuser.R
import com.example.randomuser.data.api.ApiClient
import com.example.randomuser.data.api.ApiService
import com.example.randomuser.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val apiClient by lazy { ApiClient.getApiClient()  }

    companion object{
        val TAG: String = "LifecycleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    private fun getUsers(){
        apiClient.getUsers(10).enqueue(object: Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
}
