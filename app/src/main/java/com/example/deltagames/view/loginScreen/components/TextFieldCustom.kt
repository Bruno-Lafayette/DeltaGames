package com.example.deltagames.view.loginScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.deltagames.R


@Composable
fun TextFieldCustom(input: String, placeHolder: String, keyboard: KeyboardType = KeyboardType.Text, icon: ImageVector, onInputChanged: (String) -> Unit){
    var isFieldEmpty by remember { mutableStateOf(true) }
    var hasUserInteracted by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        trailingIcon = {
            if (keyboard == KeyboardType.Password) {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.FavoriteBorder else Icons.Filled.Favorite,
                        contentDescription = if (isPasswordVisible) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Characters,
            autoCorrect = true,
            keyboardType = keyboard
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .onFocusChanged {it ->
                hasUserInteracted = it.isFocused
            }
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.cloud_white),
                shape = RoundedCornerShape(8.dp)
            ),
        value = input,
        onValueChange = { value ->
            isFieldEmpty = value.isEmpty()
            onInputChanged(value)
                        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "Icone de Email")
        },
        placeholder = {
            Text(text = placeHolder)
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        enabled = true,
        visualTransformation = if (keyboard == KeyboardType.Password && !isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    )
    if (isFieldEmpty && hasUserInteracted) {
        Text(
            text = "Campo obrigatório",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
