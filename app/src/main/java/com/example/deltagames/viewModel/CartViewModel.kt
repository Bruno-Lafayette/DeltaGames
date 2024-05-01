package com.example.deltagames.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.Produto
import com.example.deltagames.model.ResponseAddProductCart
import com.example.deltagames.repository.CartRepository
import com.example.deltagames.repository.UserRepository
import retrofit2.Callback

class CartViewModel: ViewModel() {
    private val repository = CartRepository()

    var listProduct: MutableList<Produto> = mutableStateListOf()
    private set

    fun addProductCart(product: CarrinhoItem, callback: (ResponseAddProductCart?) -> Unit) {
        repository.addProduct(product, callback)
    }
}