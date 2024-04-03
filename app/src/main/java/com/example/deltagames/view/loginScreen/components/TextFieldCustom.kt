package com.example.deltagames.view.loginScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.deltagames.R


@Composable
fun TextFieldCustom(input: String, placeHolder: String, icon: ImageVector, onInputChanged: (String) -> Unit){
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.cloud_white),
                shape = RoundedCornerShape(8.dp)
            ),
        value = input,
        onValueChange = { userEmail -> onInputChanged(userEmail) },
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
}
