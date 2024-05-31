package com.example.deltagames.viewModel

import androidx.lifecycle.ViewModel
import com.example.deltagames.model.Pedido
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.repository.OrderRespository

class OrderViewModel: ViewModel() {

    private val repository = OrderRespository()

    companion object {
        private val instance: OrderViewModel by lazy {
            OrderViewModel()
        }
        @JvmStatic
        fun getInstanceUnique(): OrderViewModel {
            return instance
        }
    }

    fun create(item: Pedido, callback: (ResponseAPI?) -> Unit) {
        repository.create(item) {response ->
            callback(response)
        }
    }
}