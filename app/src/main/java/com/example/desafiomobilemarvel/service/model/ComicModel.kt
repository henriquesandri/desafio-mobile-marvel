package com.example.desafiomobilemarvel.service.model

import com.google.gson.annotations.SerializedName

class ComicModel {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("pageCount")
    var pageCount: Int = 0

    @SerializedName("thumbnail")
    var thumbail: ImageModel? = null

}