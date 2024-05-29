package com.example.deltagames.network

import com.example.deltagames.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceViaCep {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL_ViaCep)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}