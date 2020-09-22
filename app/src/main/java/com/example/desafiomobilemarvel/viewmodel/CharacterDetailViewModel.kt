package com.example.desafiomobilemarvel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.comic.ResponseComicModel
import com.example.desafiomobilemarvel.service.repository.ComicRepository

class CharacterDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val mComicRepository = ComicRepository(application)

    private val mResponse = MutableLiveData<ResponseComicModel>()
    var comics: LiveData<ResponseComicModel> = mResponse

    fun list(characterId: Int) {
        val listener = object : APIListener<ResponseComicModel> {
            override fun onSuccess(model: ResponseComicModel) {
                mResponse.value = model
            }

            override fun onFailure(str: String) {
                mResponse.value = null
            }
        }

        mComicRepository.list(characterId, listener)
    }
}