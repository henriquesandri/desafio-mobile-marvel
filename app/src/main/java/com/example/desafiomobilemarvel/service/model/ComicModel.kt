package com.example.desafiomobilemarvel.service.model

import com.example.desafiomobilemarvel.service.model.common.ThumbnailModel
import com.google.gson.annotations.SerializedName

class ComicModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("thumbnail")
    val thumbail: ThumbnailModel
)