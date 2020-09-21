package com.example.desafiomobilemarvel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.model.character.ResponseCharacterModel
import com.example.desafiomobilemarvel.service.repository.CharacterRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mCharacterRepository = CharacterRepository(application)

    private val mResponse = MutableLiveData<ResponseCharacterModel>()
    private var limit = 20
    private var offset = 0
    var characters: LiveData<ResponseCharacterModel> = mResponse

    fun list() {

        val listener = object : APIListener<ResponseCharacterModel> {
            override fun onSuccess(model: ResponseCharacterModel) {
                mResponse.value = model
            }

            override fun onFailure(str: String) {
                mResponse.value = null
            }
        }

        mCharacterRepository.list(listener, limit, offset)

        offset += limit

    }
}