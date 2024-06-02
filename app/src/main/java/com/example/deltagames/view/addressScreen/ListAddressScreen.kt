package com.example.deltagames.view.addressScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deltagames.R
import com.example.deltagames.model.Endereco
import com.example.deltagames.view.addressScreen.components.CardAddress
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.viewModel.AddressViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = AddressViewModel.getInstanceUnique(), // Obtenha a instância da ViewModel aqui

){
    val addressList by addressViewModel.listAddress.observeAsState(emptyList())
    var addressListUpDate by remember { mutableStateOf<List<Endereco>?>(emptyList()) }
    LaunchedEffect (key1 = addressList) {
        addressViewModel.featchAddress()
        addressListUpDate = addressList
    }

    Scaffold (
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Lista de Endereços") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "")
                    }
                }
            )
        }
    ){
        Column (modifier = Modifier
            .padding(top = it.calculateTopPadding())
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween

        ){
                LazyColumn(
                    modifier = Modifier
                ) {
                    items(addressListUpDate.orEmpty()) { address ->
                        CardAddress(endereco = address, addressViewModel)
                    }
                }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
                onClick = {
                    navController.navigate(Screens.AddAddressScreen.name)

                }
            ) {
                Text(text = "Adicionar Novo")
            }
            }
        }

}