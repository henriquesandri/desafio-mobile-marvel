package com.example.desafiomobilemarvel.service.repository

import android.content.Context
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.character.ResponseCharacterModel
import com.example.desafiomobilemarvel.service.repository.remote.CharacterService
import com.example.desafiomobilemarvel.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(val context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(CharacterService::class.java)

    fun list(listener: APIListener<ResponseCharacterModel>) {

        val call: Call<ResponseCharacterModel> = mRemote.list()

        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<ResponseCharacterModel> {
            override fun onFailure(call: Call<ResponseCharacterModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<ResponseCharacterModel>,
                response: Response<ResponseCharacterModel>
            ) {
                if (response.code() != MarvelConstants.HTTP.SUCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSucess(it) }
                }
            }
        })

    }
}