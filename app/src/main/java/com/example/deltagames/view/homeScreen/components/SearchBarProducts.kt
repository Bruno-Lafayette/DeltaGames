@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.deltagames.view.homeScreen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchBarProducts(){

    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            query = text,
            onQueryChange ={
                text = it
            },
            onSearch ={
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {Text(text = "Pesquisar")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Lupa que representa o icone de pesquisar"
                )
            },
            trailingIcon = {
                if (active){
                    Icon(
                        modifier = Modifier.clickable {
                            if(text.isNotEmpty()){
                                text = ""
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Icone para X"
                    )
                }
            }

        ) {


    }



}