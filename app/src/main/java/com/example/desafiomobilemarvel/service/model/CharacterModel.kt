package com.example.desafiomobilemarvel.service.model

import com.google.gson.annotations.SerializedName

class CharacterModel {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("thumbnail")
    var thumbnail: ThumbnailModel? = null

}