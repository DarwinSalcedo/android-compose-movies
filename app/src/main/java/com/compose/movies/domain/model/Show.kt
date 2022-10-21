package com.compose.movies.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val id: Int,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
) : Parcelable