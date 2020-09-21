package com.example.desafiomobilemarvel.service.listener

interface APIListener<T> {

    fun onSuccess(model: T)

    fun onFailure(str: String)

}