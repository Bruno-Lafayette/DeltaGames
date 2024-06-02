package com.example.deltagames.view.paymentScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.deltagames.R

@Composable
fun CardBank (
    card: String,
    cvv: String,
    name: String,
    validade: String
){
    Card (
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.dark_blue_delta_games),
            contentColor = Color.White

        )
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            AsyncImage(
                contentScale = ContentScale.FillHeight,
                model = updateCardImage(card),
                contentDescription = ""
            )
        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .height(140.dp)
                .width(340.dp)
                .padding(horizontal = 16.dp)
        ){
            Text(text = formatCardNumber(card), fontSize = 24.sp)
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)

            ) {
                Text(text = "Validade ${formatExpirationDate(validade)}")
                Text(text = "Cód Segurança ${cvv.take(3)}")
            }
            Text(text = name, modifier = Modifier.padding(bottom = 32.dp))
        }
    }
}

fun formatCardNumber(cardNumber: String): String {
    val trimmedCardNumber = cardNumber.replace(" ", "").take(16)
    val formattedStringBuilder = StringBuilder()

    for (i in trimmedCardNumber.indices) {
        formattedStringBuilder.append(trimmedCardNumber[i])
        if ((i + 1) % 4 == 0 && i != trimmedCardNumber.lastIndex) {
            formattedStringBuilder.append("  ")
        }
    }

    return formattedStringBuilder.toString()
}

fun formatExpirationDate(expirationDate: String): String {
    val trimmedExpirationDate = expirationDate.take(4) // Aceita apenas os 4 primeiros caracteres
    val formattedStringBuilder = StringBuilder()

    for (i in trimmedExpirationDate.indices) {
        formattedStringBuilder.append(trimmedExpirationDate[i])
        if ((i + 1) % 2 == 0 && i != trimmedExpirationDate.lastIndex && i < 3) {
            formattedStringBuilder.append("/") // Adiciona uma barra após o mês
        }
    }

    return formattedStringBuilder.toString()
}

fun updateCardImage(number: String): String  {
    return when (number.firstOrNull()) {
        '3' -> "https://logos-world.net/wp-content/uploads/2020/11/American-Express-Logo.png"
        '4' -> "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Visa_Inc._logo.svg/2560px-Visa_Inc._logo.svg.png"
        '5' -> "https://logos-world.net/wp-content/uploads/2020/09/Mastercard-Logo.png"
        else -> ""
    }
}