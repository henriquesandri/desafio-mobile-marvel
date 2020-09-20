package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.model.comic.ResponseComicModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("characters/{characterId}/comics")
    fun list(@Path(value = "characterId", encoded = true) characterId: Int): Call<ResponseComicModel>

}