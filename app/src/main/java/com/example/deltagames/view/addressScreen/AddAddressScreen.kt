package com.example.deltagames.view.addressScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.deltagames.R
import com.example.deltagames.model.Endereco
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.view.loginScreen.components.TextFieldCustom
import com.example.deltagames.viewModel.AddressViewModel
import com.example.deltagames.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = viewModel(),
    context: ContextProvider
){
    var cep by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var numberString by remember { mutableStateOf("") }
    var number by remember { mutableStateOf(0) }

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
            Column(modifier = Modifier.padding(16.dp)) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    ){
                    TextField(
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.cloud_white),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        value =  cep,
                        onValueChange = { value ->
                            cep =  value
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Icone de localização")
                        },
                        trailingIcon = {
                            IconButton(
                                modifier = Modifier,
                                onClick = { addressViewModel.fetchCepInfo(cep) }) {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "Icone de Buscar",
                                    tint = colorResource(id = R.color.dark_blue_delta_games),
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                            }
                        },
                        placeholder = {
                            Text(text = "Digite seu CEP")
                        },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        singleLine = true,
                        enabled = true
                    )
                }

                val cepInfo by addressViewModel.cepInfo.observeAsState()

                cepInfo?.let {
                    if (it.logradouro != null){
                        Column(modifier = Modifier.padding(8.dp)) {
                            TextFieldCustom(
                                input = name,
                                placeHolder = "Nome do Endereço",
                                icon = Icons.Default.Home
                            ) {
                                name = it
                            }
                            Text("Logradouro: ${it.logradouro}")
                            TextFieldCustom(
                                input = numberString,
                                placeHolder = "Número da residencia",
                                icon = Icons.Default.Home
                            ) {
                                numberString = it
                                number = numberString.toIntOrNull() ?: 0
                            }
                            Text("Complemento: ${it.complemento}")
                            Text("Localidade: ${it.localidade}")
                            Text("UF: ${it.uf}")

                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
                                onClick = {

                                    addressViewModel.addAddress(Endereco(
                                        0,
                                        LoginViewModel.getInstanceUnique().user!!.id,
                                        name,
                                        it.logradouro,
                                        number,
                                        it.complemento,
                                        it.cep.replace("-", ""),
                                        it.localidade,
                                        it.uf
                                    )){response ->
                                        showAlertDialog(context.context, "Sucesso", response!!.message)
                                        navController.popBackStack()
                                    }
                                }
                            ) {
                                Text(text = "Salvar")
                            }
                        }
                    } else {
                        Text(text = "Endereço não encontrado")
                    }
                }
            }
        }
    }
}
