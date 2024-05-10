package com.example.deltagames.view.loginScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.example.deltagames.R


@Composable
fun TextFieldCustom(input: String, placeHolder: String, icon: ImageVector, onInputChanged: (String) -> Unit){
    var isFieldEmpty by remember { mutableStateOf(true) }
    var hasUserInteracted by remember { mutableStateOf(false) }

    TextField(
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
        enabled = true
    )
    if (isFieldEmpty && hasUserInteracted) {
        Text(
            text = "Campo obrigatório",
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
