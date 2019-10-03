package com.example.quiz.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.R
import com.example.quiz.util.Constants
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intent.extras.let {

            val listSize  = intent.extras?.getInt(Constants.EXTRA_LIST_SIZE)
            val result= intent.extras?.getInt(Constants.EXTRA_RESULT)

            tv_result.text = "$listSize sorudan $result tanesini doğru bildiniz."

        }
    }
}
