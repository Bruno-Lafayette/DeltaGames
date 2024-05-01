package com.example.deltagames.repository

import android.util.Log
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.ResponseAddProductCart
import com.example.deltagames.network.ApiService
import com.example.deltagames.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository {
    private val apiService: ApiService = RetrofitInstance.apiService

    fun addProduct(product: CarrinhoItem, callback: (ResponseAddProductCart?) -> Unit ){
        apiService.addProductCart(product).enqueue(object: Callback<ResponseAddProductCart?>{
            override fun onResponse(call: Call<ResponseAddProductCart?>, response: Response<ResponseAddProductCart?>) {
                if (response.isSuccessful){
                    println(product)
                    callback(response.body())

                }else {
                    println("################# O ERRO PODE ESTAR NO PRODUTO #################")
                    println(product)
                    println("################# ################# ################# ##########")
                    println(response.body())
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ResponseAddProductCart?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                println("++++++++++++++++++++++ DEU MUITO RUIM MANOLO +++++++++++++++++++")
                println(t.message)
                callback(null)
            }
        })
    }
}