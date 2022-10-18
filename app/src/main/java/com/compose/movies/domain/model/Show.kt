package com.compose.movies.domain.model


data class Show(
    val name: String,
    val original_name: String,
    val overview: String,
    val vote_average: Double,
    val poster_path: Boolean,
)
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