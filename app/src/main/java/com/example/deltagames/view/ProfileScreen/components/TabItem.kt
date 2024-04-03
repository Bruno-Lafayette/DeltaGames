package com.example.deltagames.view.ProfileScreen.components

import androidx.compose.runtime.Composable
import com.example.deltagames.view.loginScreen.LoginScreen
import com.example.deltagames.view.RegisterScreen.RegisterScreen

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem (val title: String, val labelButton: String, val screens: ComposableFun ){
    object Login: TabItem(
        title = "Entrar na sua conta",
        labelButton = "Entrar",
        screens = { LoginScreen() }
    )

    object Register: TabItem(
        title = "Criar Conta",
        labelButton = "Criar",
        screens = { RegisterScreen() }
    )
}