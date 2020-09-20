package com.example.desafiomobilemarvel.service.model.character

import com.example.desafiomobilemarvel.service.model.common.ThumbnailModel
import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel
)