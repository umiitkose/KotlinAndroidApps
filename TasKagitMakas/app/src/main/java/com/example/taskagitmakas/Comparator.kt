package com.example.taskagitmakas

object Comparator {

    fun compare(firsImage: Int, secondImage: Int): Winner {
        var result: Winner = Winner.DRAW
        if(firsImage == secondImage){
            result =  Winner.DRAW
        } else if (firsImage == R.drawable.rock && secondImage == R.drawable.paper){
            result =  Winner.SECOND
        } else if (firsImage == R.drawable.rock && secondImage == R.drawable.scissors){
            result =  Winner.FIRST
        } else if (firsImage == R.drawable.paper && secondImage == R.drawable.rock){
            result =  Winner.FIRST
        } else if (firsImage == R.drawable.paper && secondImage == R.drawable.scissors){
            result =  Winner.SECOND
        } else if (firsImage == R.drawable.scissors && secondImage == R.drawable.rock){
            result =  Winner.SECOND
        } else if (firsImage == R.drawable.scissors && secondImage == R.drawable.paper){
            result =  Winner.FIRST
        }

        return result

    }

    enum class Winner {
        DRAW, FIRST, SECOND
    }
}