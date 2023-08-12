package com.example.c23.core

import android.widget.ImageView
import com.squareup.picasso.Picasso

//Picasso
fun ImageView.load(url:String) {
    if (url.isNotEmpty()) {
        Picasso.get().load(url).into(this)
    }
}
