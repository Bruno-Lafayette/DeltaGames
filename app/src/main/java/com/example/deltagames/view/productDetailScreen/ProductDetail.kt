package com.example.deltagames.view.productDetailScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.deltagames.model.Produto
import com.example.deltagames.view.navigation.Screens
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

@Composable
fun ProductDetail(navController: NavController){
    val previousBackStackEntry = navController.previousBackStackEntry
    val arguments = previousBackStackEntry?.arguments
    val productString = arguments?.getString("product")

    val product = remember {
        try {
            Gson().fromJson(productString, Produto::class.java)
            println("FUNCIONEI BORA MOSTRAR: ${productString}")

        } catch (e: JsonSyntaxException) {
            println("Erro ao desserializar JSON para objeto Produto: ${e.message}")
            Produto(0, "", "", 0.0, 0.0, 0, 0, "")
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Vamos testar")
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(Screens.HomeScreen.name)

                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"                    )
                }
            }
                )
        }
    ) {contentPadding ->
        Text(text = "FUNCIONEI",
            modifier = Modifier.padding(contentPadding))

    }
}
