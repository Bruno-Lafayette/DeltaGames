package com.example.deltagames.view.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.deltagames.R
import com.example.deltagames.model.LoginRequest
import com.example.deltagames.model.Usuario
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.view.loginScreen.components.TextFieldCustom
import com.example.deltagames.viewModel.LoginViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun LoginScreen(vmLogin: LoginViewModel, contextProvider: ContextProvider){
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val loginResult: (Usuario?) -> Unit = { usuario ->
       if ( usuario?.userEmail != null ){
           vmLogin.user = usuario
           vmLogin.isActive.value = true
           showAlertDialog(contextProvider.context,
               "Login Efetuado com Sucesso",
               "Bem vindo de volta ${vmLogin.user!!.name}")
           email = ""
           pass = ""
       } else {
           showAlertDialog(contextProvider.context,"Acesso negado", "Usuário ou senha inválido")
           email = ""
           pass = ""
       }
    }

    fun startLogin() {
        coroutineScope.launch {
            vmLogin.login(LoginRequest(email, pass), loginResult)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldCustom(
            input = email,
            icon = Icons.Default.Email,
            placeHolder = "E-mail",
            keyboard = KeyboardType.Text
        ) {newValue->
            email = newValue
        }
        TextFieldCustom(
            input = pass,
            icon = Icons.Default.Lock,
            placeHolder = "Senha",
            keyboard = KeyboardType.Password
        ) {newValue->
            pass = newValue
        }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
        onClick = {
            try {
                startLogin()
            } catch (e: Exception) {
                println("Erro na chamada startLogin----> ${e.message}")
            }
        }
    ) {
        Text(text = "Login")
    }

    }

}

