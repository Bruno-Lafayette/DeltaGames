package com.example.deltagames.view.ProfileScreen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.loginScreen.LoginScreen
import com.example.deltagames.view.registerScreen.RegisterScreen
import com.example.deltagames.viewModel.LoginViewModel

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(
    val title: String,
    val subTitle: String,
    val labelButton: String,
    val screens: @Composable (contextProvider: ContextProvider)->Unit
){
    @OptIn(ExperimentalMaterial3Api::class)
    object Login: TabItem(
        title = "Entrar na sua conta",
        subTitle = "A melhor forma de se divertir comprando",
        labelButton = "Entrar",
        screens = { context -> LoginScreen(vmLogin = LoginViewModel.getInstanceUnique(), contextProvider = context) }
    )

    object Register: TabItem(
        title = "Criar Conta",
        subTitle = "Aproveite todos os beneficios de ser nosso cliente",
        labelButton = "Criar",
        screens = { context -> RegisterScreen(vmLogin = LoginViewModel.getInstanceUnique(), contextProvider = context) }
    )
}