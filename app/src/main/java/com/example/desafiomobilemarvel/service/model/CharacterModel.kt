package com.example.desafiomobilemarvel.service.model

import com.google.gson.annotations.SerializedName

class CharacterModel {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("urls")
    var urls: Array<UrlModel>? = null

    @SerializedName("thumbnail")
    var thumbnail: ImageModel? = null

    @SerializedName("comics")
    var comics: List<ResourceModel> = emptyList()

}