package com.example.taskagitmakas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mFirstImage: ImageView
    private lateinit var mSecondImage: ImageView
    private lateinit var mFirstScore: TextView
    private lateinit var mSecondScore: TextView

    private var mFirstPlayerScore = 0
    private var mSecondPlayerScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

    }

    fun setupView(){
        mFirstImage = findViewById(R.id.img_first)
        mSecondImage= findViewById(R.id.img_second)
        mFirstScore= findViewById(R.id.tv_firstscore)
        mSecondScore= findViewById(R.id.tv_secondscore)
    }

    fun playGame(view: View){
        var firstImage: Int = 0
        var secondImage: Int = 0
        var result: Comparator.Winner = Comparator.Winner.DRAW

        when(view.id){
            R.id.btn_rock -> {
                firstImage = R.drawable.rock
                secondImage = Generator.getImage()
                setImageResource(firstImage,secondImage)

                result = Comparator.compare(firstImage,secondImage)

                writeScore(result)
            }
            R.id.btn_paper -> {
                firstImage = R.drawable.paper
                secondImage = Generator.getImage()
                setImageResource(firstImage,secondImage)

                result = Comparator.compare(firstImage,secondImage)

                writeScore(result)
            }
            R.id.btn_scissor -> {
                firstImage = R.drawable.scissors
                secondImage = Generator.getImage()
                setImageResource(firstImage,secondImage)

                result = Comparator.compare(firstImage,secondImage)

                writeScore(result)
            }
        }
    }

    fun setImageResource(firstImage: Int, secondImage: Int){
        mFirstImage.setImageResource(firstImage)
        mSecondImage.setImageResource(secondImage)
    }

    fun writeScore(winner: Comparator.Winner){
        when(winner){
            Comparator.Winner.DRAW -> Toast.makeText(this, "Berabere",Toast.LENGTH_LONG).show()
            Comparator.Winner.FIRST -> {
                Toast.makeText(this, "Sen Kazandın",Toast.LENGTH_LONG).show()
                mFirstPlayerScore++
                mFirstScore.text = mFirstPlayerScore.toString()
            }
            Comparator.Winner.SECOND -> {
                Toast.makeText(this, "Rakip Kazandı",Toast.LENGTH_LONG).show()
                mSecondPlayerScore++
                mSecondScore.text = mSecondPlayerScore.toString()
            }

        }
    }

    fun reset(view: View){
        mFirstPlayerScore = 0
        mSecondPlayerScore = 0

        mFirstScore.text = mFirstPlayerScore.toString()
        mSecondScore.text = mSecondPlayerScore.toString()
    }

}
