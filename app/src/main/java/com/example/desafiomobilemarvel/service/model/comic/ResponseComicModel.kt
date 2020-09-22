package com.example.desafiomobilemarvel.service.model.comic

import com.google.gson.annotations.SerializedName

data class ResponseComicModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("data")
    val data: DataComicModel
)