package com.example.deltagames.repository

import android.util.Log
import com.example.deltagames.model.Pedido
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.network.ApiService
import com.example.deltagames.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRespository {
    private val apiService: ApiService = RetrofitInstance.apiService
    fun create(item: Pedido, callback: (ResponseAPI?) -> Unit ){
        apiService.createOrder(item).enqueue(object: Callback<ResponseAPI?> {
            override fun onResponse(call: Call<ResponseAPI?>, response: Response<ResponseAPI?>) {
                if (response.isSuccessful){
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
}