package com.example.homework_m5_3

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "42491495-8502dbb7bb426197f42072c09",
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int = 1
    ): Call<PixabayModel>
}