package com.example.movieapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.util.Constans

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String ){
        if(url.isNotEmpty()){
            Glide.with(imageView.context)
                .load(Constans.IMAGE_BASE_URL + Constans.IMAGE_W342 + url)
                .into(imageView)
        }

    }
}