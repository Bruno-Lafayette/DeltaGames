package com.example.deltagames.repository

import android.util.Log
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.model.Usuario
import com.example.deltagames.network.ApiService
import com.example.deltagames.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {
    private val apiService: ApiService = RetrofitInstance.apiService

    fun addProduct(product: CarrinhoItem, callback: (ResponseAPI?) -> Unit ){
        apiService.addProductCart(product).enqueue(object: Callback<ResponseAPI?>{
            override fun onResponse(call: Call<ResponseAPI?>, response: Response<ResponseAPI?>) {
                if (response.isSuccessful){
                    println(product)
                    callback(response.body())

                }else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ResponseAPI?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }
        })
    }

    fun removeProduct(product: CarrinhoItem, callback: (ResponseAPI?) -> Unit ){
        apiService.removeProduct(product).enqueue(object: Callback<ResponseAPI?>{
            override fun onResponse(call: Call<ResponseAPI?>, response: Response<ResponseAPI?>) {
                if (response.isSuccessful){
                    println(product)
                    callback(response.body())

                }else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ResponseAPI?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }
        })
    }

    fun getAllProducts(id: Usuario, callback: (List<CarrinhoItem>?) -> Unit){
        apiService.getAllProductsCart(id).enqueue(object : Callback<List<CarrinhoItem>?>{
            override fun onResponse(
                call: Call<List<CarrinhoItem>?>,
                response: Response<List<CarrinhoItem>?>
            ) {
                if (response.isSuccessful){
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<List<CarrinhoItem>?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }

        })
    }
}
