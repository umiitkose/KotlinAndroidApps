package com.example.randomuser.model

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("country")
    var country: String,
    @SerializedName("city")
    var city: String


    )