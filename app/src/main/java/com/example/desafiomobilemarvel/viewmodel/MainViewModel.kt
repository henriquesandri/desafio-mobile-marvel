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

    private val mList = MutableLiveData<ResponseCharacterModel>()
    var characters: LiveData<ResponseCharacterModel> = mList

    fun list() {

        val listener = object : APIListener<ResponseCharacterModel> {
            override fun onSucess(model: ResponseCharacterModel) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = null
            }
        }

        mCharacterRepository.list(listener)

    }
}