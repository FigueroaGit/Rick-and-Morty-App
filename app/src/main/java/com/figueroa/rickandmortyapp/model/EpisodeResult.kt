package com.figueroa.rickandmortyapp.model

import com.google.gson.annotations.SerializedName

data class EpisodeResult(
    @SerializedName("air_date")
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String,
)
