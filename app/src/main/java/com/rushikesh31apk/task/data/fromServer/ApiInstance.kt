package com.rushikesh31apk.task.data.fromServer

import com.rushikesh31apk.task.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiInstance {
    private val _builder =
         Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    val api = _builder
}
