package com.example.desafiomobilemarvel.service.repository

import android.content.Context
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.comic.ResponseComicModel
import com.example.desafiomobilemarvel.service.repository.remote.ComicService
import com.example.desafiomobilemarvel.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicRepository(val context: Context) : BaseRepository() {

    private val mRemote = RetrofitClient.createService(ComicService::class.java)

    fun list(characterId: Int, listener: APIListener<ResponseComicModel>) {
        val call: Call<ResponseComicModel> = mRemote.list(characterId)

        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<ResponseComicModel> {
            override fun onFailure(call: Call<ResponseComicModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<ResponseComicModel>,
                response: Response<ResponseComicModel>
            ) {
                if (response.code() != MarvelConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }
        })
    }
}