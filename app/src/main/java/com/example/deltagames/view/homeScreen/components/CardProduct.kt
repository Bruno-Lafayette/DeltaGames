package com.example.deltagames.view.homeScreen.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.deltagames.model.Produto
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.viewModel.SharedProductViewModel


@Composable
fun CardProduct(product: Produto, navController: NavController, sharedProductViewModel: SharedProductViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(148.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(148.dp)
                    .clickable {
                        try {
                            product?.let { nonNullProduct ->
                                sharedProductViewModel.addProduct(nonNullProduct)
                                navController.navigate(Screens.ProductDetail.name)
                            }
                        } catch (e: Exception) {
                            println("Erro ao serializar objeto para JSON: ${e.message}")
                        }
                    },
                contentScale = ContentScale.FillBounds,
                model = product.IMAGEM_URL,
                contentDescription = ""
            )
        }
        Text(
            text = product.PRODUTO_NOME,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            fontSize = 16.sp
        )
    }

}

