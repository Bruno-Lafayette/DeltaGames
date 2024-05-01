package com.example.deltagames.viewModel

import androidx.lifecycle.ViewModel
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Usuario
import com.example.deltagames.repository.UserRepository

class LoginViewModel: ViewModel() {

    companion object {
        private val instance: LoginViewModel by lazy {
            LoginViewModel()
        }
        @JvmStatic
        fun getInstanceUnique(): LoginViewModel {
            return instance
        }
    }

    private val repository = UserRepository()
    var user: Usuario? = null
    var isActive = false
    fun login(loginRequest: LoginRequest, callback: (Usuario?) -> Unit) {
        repository.login(loginRequest, callback)
    }
}