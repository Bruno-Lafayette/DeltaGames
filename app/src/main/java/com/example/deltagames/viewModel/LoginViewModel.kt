package com.example.deltagames.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Usuario
import com.example.deltagames.repository.UserRepository

class LoginViewModel: ViewModel() {

    private val repository = UserRepository()
    var user: Usuario? = null
    var isActive = MutableLiveData<Boolean>().apply { value = false }

    companion object {
        private val instance: LoginViewModel by lazy {
            LoginViewModel()
        }
        @JvmStatic
        fun getInstanceUnique(): LoginViewModel {
            return instance
        }
    }

    fun login(loginRequest: LoginRequest, callback: (Usuario?) -> Unit) {
        repository.login(loginRequest, callback)
    }
}