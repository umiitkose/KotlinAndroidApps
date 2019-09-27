package com.example.randomuser.model

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("street")
    var street: String,
    @SerializedName("city")
    var city: String


    )