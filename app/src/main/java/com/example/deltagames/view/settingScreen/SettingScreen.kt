package com.example.deltagames.view.settingScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deltagames.model.Endereco
import com.example.deltagames.util.component.ButtonListAddress
import com.example.deltagames.viewModel.AddressViewModel
import com.example.deltagames.viewModel.LoginViewModel

@Composable
fun SettingScreen(
    userViewModel: LoginViewModel,
    navController: NavController
){


    val addresses by AddressViewModel.getInstanceUnique().listAddress.observeAsState(emptyList())
    var addressUpDate by remember { mutableStateOf<List<Endereco>>(emptyList()) }

    LaunchedEffect(key1 = addresses) {
        addressUpDate = addresses!!
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {
                    Text(
                        text = "Perfil",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )
                },
                actions = {
                    IconButton(onClick = {
                        userViewModel.isActive.postValue(false)
                        navController.popBackStack(navController.graph.startDestinationId, false)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.ExitToApp,
                            contentDescription = "Icone de sair"
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(top = contentPadding.calculateTopPadding())
                .padding(16.dp),
        ) {
            Text(text = "Usuario: ${userViewModel.user!!.name}")
            Text(text = "Email: ${userViewModel.user!!.userEmail}")
            Text(text = "CPF: ${userViewModel.user!!.cpf}")
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Endereço",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
                )
            ButtonListAddress(navigationController = navController,
                addressUpDate.getOrNull(0)
            )
        }
    }
}