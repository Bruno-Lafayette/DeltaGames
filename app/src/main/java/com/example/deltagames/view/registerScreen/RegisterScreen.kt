package com.example.deltagames.view.registerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.deltagames.R
import com.example.deltagames.model.Usuario
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.view.loginScreen.components.TextFieldCustom
import com.example.deltagames.viewModel.LoginViewModel

@Composable
fun RegisterScreen(vmLogin: LoginViewModel, contextProvider: ContextProvider){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white))
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var cpf by remember { mutableStateOf("") }

        TextFieldCustom(
            input = name,
            icon = Icons.Default.AccountCircle,
            placeHolder = "Nome completo"
        ) { newValue ->
            name = newValue
        }
        TextFieldCustom(
            input = email,
            icon = Icons.Default.Email,
            placeHolder = "E-mail"
        ) { newValue ->
            email = newValue
            println(newValue)
        }
        TextFieldCustom(
            input = pass,
            icon = Icons.Default.Lock,
            placeHolder = "Senha"
        ) { newValue ->
            pass = newValue
        }
        TextFieldCustom(
            input = cpf,
            icon = Icons.Default.AccountBox,
            placeHolder = "CPF"
        ) { newValue ->
            cpf = newValue
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
            onClick = {
                vmLogin.register(Usuario(0, name, email, cpf, pass)) {
                    it?.let { it1 ->
                        showAlertDialog(
                            contextProvider.context,
                            "",
                            it1.message
                        )
                    }
                }
            }
        ) {
            Text(text = "Criar")
        }
    }
}
