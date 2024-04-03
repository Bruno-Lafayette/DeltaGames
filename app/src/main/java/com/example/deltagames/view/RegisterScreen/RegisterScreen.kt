package com.example.deltagames.view.RegisterScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RegisterScreen(){
    Box(modifier = Modifier.background(color = Color.White)){
        Text(text = "Registro de novo usuario")
    }
}
