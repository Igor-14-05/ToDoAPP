package com.example.yandextodoapp.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.yandextodoapp.app.App
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASEURL = "https://hive.mrdekk.ru/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(App.context).build())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASEURL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun mainApi(): MainApi = retrofit.create(MainApi::class.java)
}