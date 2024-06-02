package com.example.deltagames.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deltagames.model.Endereco
import com.example.deltagames.model.ResponseAPI
import com.example.deltagames.repository.AddressRepository
import kotlinx.coroutines.launch

class AddressViewModel: ViewModel() {

    private val repository = AddressRepository()
    private val _listAddress = MutableLiveData<List<Endereco>>()
    val listAddress = _listAddress
    private val _cepInfo = MutableLiveData<Endereco?>()
    val cepInfo: MutableLiveData<Endereco?> = _cepInfo

    init {
        featchAddress()
    }

    companion object {
        private val instance: AddressViewModel by lazy {
            AddressViewModel()
        }
        @JvmStatic
        fun getInstanceUnique(): AddressViewModel {
            return instance
        }
    }

    fun addAddress(endereco: Endereco, callback: (ResponseAPI?) -> Unit){
        repository.addAddress(endereco){
            featchAddress()
            callback(it)
        }
    }

    fun removeAddress(endereco: Endereco, callback: (ResponseAPI?) -> Unit){
        repository.removeAddress(endereco){
            featchAddress()
            callback(it)
        }
    }

    fun featchAddress(){
        repository.listAddress(LoginViewModel.getInstanceUnique().user!!.id){address ->
            address.let {
                _listAddress.postValue(it)
            }

        }
    }

    fun fetchCepInfo(cep: String) {
        viewModelScope.launch {
            repository.getCepInfo(cep) { info ->
                if (info != null) {
                    _cepInfo.postValue(info)
                }
            }
        }
    }
}