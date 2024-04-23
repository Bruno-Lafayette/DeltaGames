package com.example.deltagames.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deltagames.model.Produto
import com.example.deltagames.repository.ProductRepository
import com.example.deltagames.util.ContextProvider
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel: ViewModel() {

    private val repository = ProductRepository()
    private val _products = MutableLiveData<List<Produto>>()
    val products = _products
    fun fetchProducts(){
        viewModelScope.launch {
            try {
                val response = repository.getProducts()
                if (response.isNotEmpty()){
                    products.value = response
                }
            } catch(e: Exception) {
                println(e)
            }
        }
    }
}