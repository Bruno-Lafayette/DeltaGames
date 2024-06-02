package com.example.deltagames.view.addressScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.model.Endereco
import com.example.deltagames.viewModel.AddressViewModel

@Composable
fun CardAddress(
    endereco: Endereco,
    vmAddress: AddressViewModel
){
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Row {
                    Icon(
                        imageVector = Icons.Sharp.LocationOn,
                        contentDescription = "Icone de localização"
                    )
                    Text(text = endereco.nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)

                }
                Text(text = "CEP: ${endereco.cep} - Estado: ${endereco.uf}")
            }
            IconButton(onClick = {
                vmAddress.removeAddress(endereco){

                }
            }) {
                Icon(
                    imageVector = Icons.TwoTone.Delete,
                    contentDescription = "Deletar",
                    tint = Color.Red
                )
            }
        }
    }

}