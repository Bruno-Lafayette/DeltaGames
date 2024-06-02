package com.example.deltagames.view.cartScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.deltagames.R
import com.example.deltagames.model.Endereco


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAddress(
    selectedValue: String,
    options: List<Endereco>,
    label: String,
    onValueChangedEvent: (Endereco) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.orange_delta_games),
                focusedLabelColor = colorResource(id = R.color.orange_delta_games),
                focusedContainerColor = Color.Transparent,
                focusedPlaceholderColor = colorResource(id = R.color.orange_delta_games),
                focusedTrailingIconColor = colorResource(id = R.color.orange_delta_games),

                unfocusedContainerColor = colorResource(id = R.color.dark_blue_delta_games),
                unfocusedBorderColor = colorResource(id = R.color.dark_blue_delta_games),
                unfocusedPlaceholderColor = Color.White,
                unfocusedLabelColor = Color.White,
                unfocusedTrailingIconColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(8.dp)
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option: Endereco ->
                DropdownMenuItem(
                    text = { Text(text = option.nome) },
                    onClick = {
                        expanded = false
                        onValueChangedEvent(option)
                    }
                )
            }
        }
    }
}