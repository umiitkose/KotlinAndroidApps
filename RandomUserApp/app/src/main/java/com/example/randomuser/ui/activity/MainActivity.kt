package com.example.randomuser.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuser.R
import com.example.randomuser.data.api.ApiClient
import com.example.randomuser.data.api.ApiService
import com.example.randomuser.model.UserResponse
import com.example.randomuser.ui.adapter.UserAdapter
import com.example.randomuser.util.gone
import com.example.randomuser.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val apiClient by lazy { ApiClient.getApiClient()  }
    private lateinit var adapter: UserAdapter
    companion object{
        val TAG: String = "LifecycleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_user_lists.layoutManager = LinearLayoutManager(this)
        rv_user_lists.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        progressBar.visible()
        rv_user_lists.gone()
        getUsers()

        swipe_refresh_layout.setOnRefreshListener {
            getUsers()
        }

    }

    private fun getUsers(){
        apiClient.getUsers(10).enqueue(object: Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, t.message)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
              if(response.isSuccessful){
                 // Log.d(TAG, response.body()?.userList?.get(1).toString())
                  adapter =  UserAdapter(response.body()?.userList!!)
                  rv_user_lists.adapter= adapter
                  progressBar.gone()
                  rv_user_lists.visible()
                  swipe_refresh_layout.isRefreshing= false
              }
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val searchItem= menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }

        })

        return true
    }
}
