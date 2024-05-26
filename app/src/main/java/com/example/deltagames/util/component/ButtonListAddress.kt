package com.example.deltagames.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deltagames.view.navigation.Screens

@Composable
fun ButtonListAddress(navigationController: NavController){
    Column (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp)
            .clickable {
                navigationController.navigate(Screens.ListAddressScreen.name)
            }
    ) {
        Text(
            text = "Endereço",
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column (modifier = Modifier.fillMaxHeight()) {
                Text(text = "${"Casa"}")
                Text(
                    text = "${"Rua joao fernandes camisa nova junior"}, ${174} ",
                    maxLines = 2
                )
            }
            Image(
                imageVector = Icons.Sharp.KeyboardArrowRight,
                contentDescription = "Seta indicando lado direito",
                modifier = Modifier
                    .width(20.dp)
            )
        }
    }
}