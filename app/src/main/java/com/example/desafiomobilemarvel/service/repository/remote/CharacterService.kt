package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.model.character.ResponseCharacterModel
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {

    @GET("characters")
    fun list(): Call<ResponseCharacterModel>

}