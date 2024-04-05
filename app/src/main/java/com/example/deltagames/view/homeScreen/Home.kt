package com.example.deltagames.view.homeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.TopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deltagames.view.homeScreen.components.CardProduct
import com.example.deltagames.view.homeScreen.components.SearchBarProducts
import com.example.deltagames.viewModel.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val products by viewModel.products.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {
                Text(
                    text = "Jogos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black
                )
            }
            )
        }
    ) {contentPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding())

        ) {
            if (products.isEmpty()){
                Text(text = "Carregando")
            } else {
                SearchBarProducts()
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalItemSpacing = 8.dp,
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(products) { product ->
                        CardProduct(product = product, navController = navController)
                    }
                }
            }
        }
    }
}

