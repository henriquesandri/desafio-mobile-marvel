package com.example.desafiomobilemarvel.service.listener

interface APIListener<T> {

    fun onSucess(model: T)

    fun onFailure(str: String)

}