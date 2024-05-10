package com.example.deltagames.view.cartScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.model.Produto

@Composable
fun CardPaymentDetail(
    productDetails: List<Pair<Produto, Int>>
){
    var totalPrice = 0.0
    for (item in productDetails) {
        totalPrice += item.second * (item.first.PRODUTO_PRECO - item.first.PRODUTO_DESCONTO)
    }
    Column (modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(horizontal = 8.dp)){
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = "Detalhes do Pagamento",
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ){
            Text(text = "Valor dos produtos")
            Text(text = "R$${String.format("%.2f", totalPrice)}")
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Entrega")
            Text(text = "R$5,99")
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                fontSize = 18.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold,
                text = "R$${String.format("%.2f", (totalPrice + 5.99))}"
            )
        }
    }
}