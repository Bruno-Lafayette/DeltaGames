package com.example.deltagames.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.deltagames.model.Produto

class SharedProductViewModel: ViewModel() {
    var product by mutableStateOf<Produto?>(null)
        private set

    fun addProduct(newProduct: Produto){
        product = newProduct
    }

    private fun checkPriceProduct(price: Double?, desc: Double?): Double{
        return  (price ?: 0.0) - (desc ?: 0.0)
    }

    fun priceStringFormat(): Pair<String, Boolean> {
        val price = checkPriceProduct(product?.PRODUTO_PRECO, product?.PRODUTO_DESCONTO )
        return if (price > 0.0){
            Pair("R$%.2f".format(price), true)
        } else {
            Pair("Produto Indisponível", false)
        }
    }
}