package com.example.desafiomobilemarvel.service.model.character

import com.google.gson.annotations.SerializedName

data class ResponseCharacterModel(
    @SerializedName("code")
    val code: Int,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("data")
    val data: DataCharacterModel
)