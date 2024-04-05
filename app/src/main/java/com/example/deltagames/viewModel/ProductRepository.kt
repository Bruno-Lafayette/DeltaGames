package com.example.deltagames.viewModel

import com.example.deltagames.model.Produto
import com.example.deltagames.network.ApiService
import com.example.deltagames.network.RetrofitInstance

class ProductRepository {
    private val productService = RetrofitInstance.productService
    suspend fun getProducts(): List<Produto> {
        return productService.getProducts()
    }
}