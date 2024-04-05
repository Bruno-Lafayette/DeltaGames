package com.example.deltagames.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private  const val BASE_URL = "https://a00a9cd4-e53b-425b-a8e1-4e6b7c69193b-00-1dxt5efw6itwa.worf.replit.dev"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val productService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}