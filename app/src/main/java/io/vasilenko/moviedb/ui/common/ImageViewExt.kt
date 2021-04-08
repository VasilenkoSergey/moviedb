package io.vasilenko.moviedb.ui.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.load(path: String) {
    Picasso.get()
        .load(path)
        .into(this)
}
