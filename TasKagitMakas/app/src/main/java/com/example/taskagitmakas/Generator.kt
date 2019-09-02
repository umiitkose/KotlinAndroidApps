package com.example.taskagitmakas

import java.util.*

object Generator {

    fun generateRandomNumber(): Int = Random().nextInt(3) //0,1,2

    fun getImage(): Int{
        val images = listOf(R.drawable.rock,R.drawable.paper,R.drawable.scissors)
        return images[generateRandomNumber()]
    }
}