package com.example.deltagames.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.Item
import com.example.deltagames.model.Produto
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.repository.CartRepository

class CartViewModel: ViewModel() {
    private val repository = CartRepository()
    private val _carItems = MutableLiveData<List<CarrinhoItem>?>()
    val cartItems: MutableLiveData<List<CarrinhoItem>?> = _carItems
    fun addProductCart(product: CarrinhoItem, callback: (ResponseAPI?) -> Unit) {
        repository.addProduct(product) {response ->
            fechProductsCart()
            callback(response)
        }
    }
    fun removeProductCart(product: CarrinhoItem, callback: (ResponseAPI?) -> Unit){
        repository.removeProduct(product) { response ->
            fechProductsCart()
            callback(response)
        }
    }
    fun listItens(products: List<Produto>?, cartItems: List<CarrinhoItem>?): List<Item> {
        val listItens = mutableListOf<Item>()
        if (!cartItems.isNullOrEmpty() && products != null) {
            for (item in cartItems) {
                val result = products.find { it.PRODUTO_ID == item.id }
                if (result != null) {
                    listItens.add(Item(result.PRODUTO_ID, item.qtd, (result.PRODUTO_PRECO - result.PRODUTO_DESCONTO)))
                }
            }
        }
        return listItens
    }
    fun listProducts (products: List<Produto>?, cartItems: List<CarrinhoItem>?): List<Pair<Produto, Int>> {
        val productDetails = mutableListOf<Pair<Produto, Int>>()
        if (!cartItems.isNullOrEmpty() && products != null) {
            for (item in cartItems) {
                val result = products.find { it.PRODUTO_ID == item.id }
                if (result != null) {
                    productDetails.add(Pair(result, item.qtd))
                }
            }
        }
        return productDetails
    }
    fun fechProductsCart() {
        repository.getAllProducts(LoginViewModel.getInstanceUnique().user!!) { listaProdutos ->
            if (listaProdutos != null) {
                _carItems.postValue(listaProdutos)
            }
        }
    }
    fun updateProductQuantity(productId: Int, newQuantity: Int) {
        val currentItems = _carItems.value ?: return
        val updatedItems = currentItems.map { item ->
            if (item.id == productId) {
                item.copy(qtd = newQuantity)
            } else {
                item
            }
        }
        _carItems.value = updatedItems
    }
}
