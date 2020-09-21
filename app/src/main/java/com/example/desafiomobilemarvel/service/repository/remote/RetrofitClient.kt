package com.example.desafiomobilemarvel.service.repository.remote

import com.example.desafiomobilemarvel.service.constants.MarvelConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {

    companion object {

        private const val baseUrl = "http://gateway.marvel.com/v1/public/"

        private fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private fun getOkHttpClient() = OkHttpClient().newBuilder()
            .addInterceptor { chain ->
                val currentTimestamp = System.currentTimeMillis()
                val newUrl = chain.request().url()
                    .newBuilder()
                    .addQueryParameter(MarvelConstants.PARAMS.TS, currentTimestamp.toString())
                    .addQueryParameter(MarvelConstants.PARAMS.API_KEY, MarvelConstants.KEYS.PUBLIC)
                    .addQueryParameter(
                        MarvelConstants.PARAMS.HASH,
                        toMD5Hash(currentTimestamp.toString() + MarvelConstants.KEYS.PRIVATE + MarvelConstants.KEYS.PUBLIC)
                    )
                    .build()

                val newRequest = chain.request()
                    .newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            }
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .build()

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }

        private fun toMD5Hash(toBeEncrypt: String): String {
            var pass = toBeEncrypt
            var encryptedString: String? = null
            val md5: MessageDigest
            try {
                md5 = MessageDigest.getInstance("MD5")
                md5.update(pass.toByteArray(), 0, pass.length)
                pass = BigInteger(1, md5.digest()).toString(16)
                while (pass.length < 32) {
                    pass = "0$pass"
                }
                encryptedString = pass
            } catch (e1: NoSuchAlgorithmException) {
                e1.printStackTrace()
            }
            Timber.d("hash -> $encryptedString")
            return encryptedString ?: ""
        }

    }

}