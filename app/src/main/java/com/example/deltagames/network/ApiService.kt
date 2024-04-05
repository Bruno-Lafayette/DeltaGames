package com.example.deltagames.network

import com.example.deltagames.model.Produto
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getProducts(): List<Produto>
}