package com.compose.movies.framework

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.compose.movies.R
import com.compose.movies.framework.AppConstants.IMAGE_URL

fun ImageView.loadUrl(imgUrl: String) {
    val imgUri = "$IMAGE_URL$imgUrl".toUri().buildUpon().scheme("https").build()
    Glide.with(this.context)
        .load(imgUri)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}