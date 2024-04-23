package com.example.deltagames.repository

import com.example.deltagames.model.Produto
import com.example.deltagames.network.RetrofitInstance

class ProductRepository {
    private val apiService = RetrofitInstance.apiService
    suspend fun getProducts(): List<Produto> {
        return apiService.getProducts()
    }
}