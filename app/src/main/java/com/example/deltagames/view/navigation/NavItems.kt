package com.example.deltagames.view.navigation

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label:  String,
    val icon:   ImageVector,
    val route:  String
)

val listOfNavItems = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Pesquisa",
        icon = Icons.Default.Search,
        route = Screens.SearchScreen.name
    ),
    NavItem(
        label = "Carrinho",
        icon = Icons.Default.ShoppingCart,
        route = Screens.CartScreen.name
    ),
    NavItem(
        label = "Perfil",
        icon = Icons.Default.AccountCircle,
        route = Screens.ProfileScreen.name
    ),
)