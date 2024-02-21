package com.example.homework_m5_3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixabayService {
    private val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val api: PixabayApi = retrofit.create(PixabayApi::class.java)
}
