package com.example.randomuser.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomuser.R
import com.example.randomuser.model.Results
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.lang.Exception

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportStartPostponedEnterTransition()

        val results: Results = intent.extras?.getSerializable(MainActivity.EXTRA_RESULT_ITEM) as Results

        tv_detail_username.text = "${results.name.first} ${results.name.last}"
        tv_detail_email.text = results.email
        tv_detail_adress.text = "${results.location.city} ${results.location.country}"


        val imageTransitionName = intent.extras!!.getString(MainActivity.EXTRA_RESULT_TRANSITION_NAME)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            detail_user_image.transitionName = imageTransitionName
        }

        Picasso.get().load(results.picture.large).into(detail_user_image,object: Callback{
            override fun onSuccess() {
                supportStartPostponedEnterTransition()
            }

            override fun onError(e: Exception?) {
                supportStartPostponedEnterTransition()
            }

        })
    }
}
