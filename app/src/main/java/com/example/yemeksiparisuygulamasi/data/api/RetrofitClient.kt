package com.example.yemeksiparisuygulamasi.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://kasimadalan.pe.hu/yemekler/"

    val yemeklerService: YemeklerService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YemeklerService::class.java)
    }
}