package com.compose.movies.domain.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowDto(
    @Json(name = "page") val page: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "results") val results: List<ShowItemDto>,
) {

    data class ShowItemDto(
        @Json(name = "backdrop_path") val backDropImage: String,
        @Json(name = "poster_path") val posterPatch: String,
        @Json(name = "first_air_date") val firstAirDate: String,
        @Json(name = "name") val name: String,
        @Json(name = "original_name") val originalName: String,
        @Json(name = "overview") val overView: String,
        @Json(name = "popularity") val popularity: String,
    )

}

/*

{
    "backdrop_path": "/ajztm40qDPqMONaSJhQ2PaNe2Xd.jpg",
    "first_air_date": "2022-09-21",
    "genre_ids": [
    10765,
    10759,
    10768
    ],
    "id": 83867,
    "name": "Star Wars: Andor",
    "origin_country": [
    "US"
    ],
    "original_language": "en",
    "original_name": "Star Wars: Andor",
    "overview": "The tale of the burgeoning rebellion against the Empire and how people and planets became involved. In an era filled with danger, deception and intrigue, Cassian Andor embarks on the path that is destined to turn him into a rebel hero.",
    "popularity": 723.621,
    "poster_path": "/59SVNwLfoMnZPPB6ukW6dlPxAdI.jpg",
    "vote_average": 7.9,
    "vote_count": 149
},*/