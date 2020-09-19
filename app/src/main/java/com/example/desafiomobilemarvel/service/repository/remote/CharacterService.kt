package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.model.CharacterModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    @GET("v1/public/characters")
    fun list(): Call<List<CharacterModel>>

}