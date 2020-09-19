package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.model.ComicModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("v1/public/characters/{characterId}/comics")
    fun list(@Path(value = "characterId", encoded = true) characterId: Int): Call<List<ComicModel>>

}