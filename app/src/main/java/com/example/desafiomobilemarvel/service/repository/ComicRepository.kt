package com.example.desafiomobilemarvel.service.repository

import android.content.Context
import com.example.desafiomobilemarvel.R
import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.ComicModel
import com.example.desafiomobilemarvel.service.repository.remote.ComicService
import com.example.desafiomobilemarvel.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ComicRepository(val context: Context) : BaseRepository(context) {

    private val mRemote = RetrofitClient.createService(ComicService::class.java)

    private fun list(
        call: Call<List<ComicModel>>,
        listener: APIListener<List<ComicModel>>
    ) {

        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        call.enqueue(object : Callback<List<ComicModel>> {
            override fun onFailure(call: Call<List<ComicModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<List<ComicModel>>,
                response: Response<List<ComicModel>>
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