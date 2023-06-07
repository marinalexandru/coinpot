package com.example.myapplication.ui.shared

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl)
        .into(this)
}
