package com.example.desafiomobilemarvel.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafiomobilemarvel.service.listener.APIListener
import com.example.desafiomobilemarvel.service.listener.ValidationListener
import com.example.desafiomobilemarvel.service.model.CharacterModel
import com.example.desafiomobilemarvel.service.repository.CharacterRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mCharacterRepository = CharacterRepository(application)

    private val mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    private val mList = MutableLiveData<List<CharacterModel>>()
    var characters: LiveData<List<CharacterModel>> = mList

    fun list() {

        val listener = object : APIListener<List<CharacterModel>> {
            override fun onSucess(model: List<CharacterModel>) {
                mList.value = model
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
                mValidation.value = ValidationListener(str)
            }
        }

        mCharacterRepository.all(listener)

    }
}