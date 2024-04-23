package com.example.deltagames.repository

import android.util.Log
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Usuario
import com.example.deltagames.network.ApiService
import com.example.deltagames.network.RetrofitInstance
import retrofit2.http.Body
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private val apiService: ApiService = RetrofitInstance.apiService

    fun login(loginRequest: LoginRequest, callback: (Usuario?) -> Unit) {
        try {
            apiService.login(loginRequest).enqueue(object: Callback<Usuario?> {
                override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                    if (response.isSuccessful) {
                        callback(response.body())
                    } else {
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                    Log.e("UserRepository", "Falha na chamada Retrofit: ${t.message}", t)
                    callback(null)
                }
            })
        } catch (e: Exception) {
            Log.e("UserRepository", "Erro na chamada Retrofit: ${e.message}", e)
            callback(null)
        }
    }
}
