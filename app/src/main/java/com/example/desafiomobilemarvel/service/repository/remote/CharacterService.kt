package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.model.character.ResponseCharacterModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("characters")
    fun list(
        @Query(MarvelConstants.PARAMS.LIMIT) limit: Int,
        @Query(MarvelConstants.PARAMS.OFFSET) offset: Int
    ): Call<ResponseCharacterModel>

}