package com.example.deltagames.view.addressScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deltagames.R
import com.example.deltagames.view.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAddressScreen(
    navController: NavController,
){
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
        Column (modifier = Modifier.padding(top = it.calculateTopPadding())
            .fillMaxSize()
        ){
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