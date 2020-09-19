package com.example.desafiomobilemarvel.service.model

import com.google.gson.annotations.SerializedName

class ResourceModel {

    @SerializedName("available")
    var available: Int = 0

    @SerializedName("returned")
    var returned: Int = 0

    @SerializedName("collectionURI")
    var collectionURI: String = ""

}