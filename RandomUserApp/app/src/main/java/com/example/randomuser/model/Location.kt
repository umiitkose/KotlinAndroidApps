package com.example.randomuser.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Location(

    @SerializedName("country")
    var country: String,
    @SerializedName("city")
    var city: String

): Serializable