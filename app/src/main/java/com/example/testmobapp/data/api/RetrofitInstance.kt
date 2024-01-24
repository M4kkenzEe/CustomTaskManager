package com.example.testmobapp.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val url = "http://192.168.0.104:8000"
//    val url = "http://127.0.0.1:8000"

    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val gsonConverter = GsonConverterFactory.create()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(gsonConverter)
        .client(client)
        .build()

    val api by lazy {
        retrofit.create(ApiService::class.java)
    }
}