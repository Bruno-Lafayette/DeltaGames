package com.example.deltagames.viewModel

import androidx.lifecycle.ViewModel
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Usuario
import com.example.deltagames.repository.UserRepository

class LoginViewModel: ViewModel() {
    private val repository = UserRepository()
    suspend fun login(loginRequest: LoginRequest, callback: (Usuario?) -> Unit) {
        repository.login(loginRequest, callback)
    }
}