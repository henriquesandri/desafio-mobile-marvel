package com.example.desafiomobilemarvel.service.repository

import android.content.Context
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.CharacterModel
import com.example.desafiomobilemarvel.service.repository.remote.CharacterService
import com.example.desafiomobilemarvel.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository(val context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(CharacterService::class.java)

    fun all(listener: APIListener<List<CharacterModel>>) {
        val call: Call<List<CharacterModel>> = mRemote.list()
        list(call, listener)
    }

    private fun list(
        call: Call<List<CharacterModel>>,
        listener: APIListener<List<CharacterModel>>
    ) {

        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<List<CharacterModel>> {
            override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<List<CharacterModel>>,
                response: Response<List<CharacterModel>>
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