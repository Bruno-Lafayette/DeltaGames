package com.example.deltagames.network

import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Produto
import com.example.deltagames.model.ResponseAddProductCart
import com.example.deltagames.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("products/")
    suspend fun getProducts(): List<Produto>
    @POST("auth/login")
    fun login( @Body loginRequest: LoginRequest): Call<Usuario>
    @POST("cart/addProduct")
    fun addProductCart(@Body carrinhoItem: CarrinhoItem): Call<ResponseAddProductCart>
}