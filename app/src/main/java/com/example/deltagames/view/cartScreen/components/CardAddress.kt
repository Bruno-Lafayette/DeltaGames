package com.example.deltagames.view.cartScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.twotone.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun CardAddress(){
    Column (

        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 8.dp)){
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            text = "Endereço de Entrega",
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ){
            Row (verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Localização"
                )
                Column {
                    Text(text = "Jalan Kloposepuluh Kec Sukodono,")
                    Text(text = "Kab Sidoarjo Jawa Timur")
                }
            }
            Icon(
                imageVector = Icons.TwoTone.KeyboardArrowRight,
                contentDescription = "Seta indicando para direita"
            )
        }
    }
}