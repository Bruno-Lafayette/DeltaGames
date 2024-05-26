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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deltagames.util.component.ButtonListAddress
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.viewModel.LoginViewModel

@Composable
fun SettingScreen(
    userViewModel: LoginViewModel,
    navController: NavController
){
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
                        userViewModel.user = null
                        navController.navigate(Screens.ProfileScreen.name)
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
            ButtonListAddress(navigationController = navController)
        }
    }
}