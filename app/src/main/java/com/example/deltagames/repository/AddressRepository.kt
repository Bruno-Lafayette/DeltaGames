package com.example.deltagames.repository

import android.util.Log
import com.example.deltagames.model.Endereco
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.network.RetrofitInstance
import com.example.deltagames.network.RetrofitInstanceViaCep
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    private val apiService = RetrofitInstance.apiService
    private val apiServiceViaCep = RetrofitInstanceViaCep.apiService

    fun listAddress(userID: Int, callback: (List<Endereco>?) -> Unit){
        apiService.listAddress(Endereco(usuario_id = userID)).enqueue(object: Callback<List<Endereco>?>{
            override fun onResponse(
                call: Call<List<Endereco>?>,
                response: Response<List<Endereco>?>
            ) {
                if (response.isSuccessful){
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<List<Endereco>?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }
        })
    }

    fun addAddress(endereco: Endereco, callback: (ResponseAPI?) -> Unit){
        apiService.addAddress(endereco).enqueue(object: Callback<ResponseAPI?>{
            override fun onResponse(
                call: Call<ResponseAPI?>,
                response: Response<ResponseAPI?>
            ) {
                if (response.isSuccessful){
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ResponseAPI?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }
        })
    }

    fun removeAddress(endereco: Endereco, callback: (ResponseAPI?) -> Unit){
        apiService.removeAddress(endereco).enqueue(object: Callback<ResponseAPI?>{
            override fun onResponse(
                call: Call<ResponseAPI?>,
                response: Response<ResponseAPI?>
            ) {
                if (response.isSuccessful){
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<ResponseAPI?>, t: Throwable) {
                Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                callback(null)
            }
        })
    }
    

    fun getCepInfo(cep: String, callback: (Endereco?) -> Unit) {
        val call = apiServiceViaCep.getCepInfo(cep)
        call.enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {

                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
            override fun onFailure(call: Call<Endereco>, t: Throwable) {
                callback(null)
            }
        })
    }
}