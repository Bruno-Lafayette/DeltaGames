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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deltagames.model.Endereco
import com.example.deltagames.view.navigation.Screens

@Composable
fun ButtonListAddress(navigationController: NavController, endereco: Endereco?){

    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navigationController.navigate(Screens.ListAddressScreen.name)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column (
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(60.dp)
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    if (endereco == null){
                        Text(text = "Nenhum endereço encontrado")
                        Text(text = "Clique aqui para cadastrar")
                    } else {
                        Row {
                            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "icone de localização")
                            Text(text = "${endereco.nome}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        Text(
                            text = "${endereco.logradouro}, ${endereco.numero} ",
                            maxLines = 2
                        )
                    }
                }
                Image(
                    imageVector = Icons.Sharp.KeyboardArrowRight,
                    contentDescription = "Seta indicando lado direito",
                    modifier = Modifier
                        .width(20.dp)
                        .fillMaxHeight()
                )
            }
        }
    }



}