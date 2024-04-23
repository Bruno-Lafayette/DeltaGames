package com.example.deltagames.view.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deltagames.model.Produto
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.CartScreen.CartScreen
import com.example.deltagames.view.homeScreen.HomeScreen
import com.example.deltagames.view.ProfileScreen.ProfileScreen
import com.example.deltagames.view.SearchScreen.SearchScreen
import com.example.deltagames.view.productDetailScreen.ProductDetail
import com.example.deltagames.viewModel.HomeViewModel
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@Composable
 fun AppNavigation(viewModel: HomeViewModel, context: ContextProvider ) {
     val navController = rememberNavController()
    Scaffold (bottomBar = {

        NavigationBar {
            val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
            val currentDestinarion = navBackStackEntry?.destination

            listOfNavItems.forEach {navItem ->
                NavigationBarItem(
                    selected = currentDestinarion?.hierarchy?.any() {it.route == navItem.route} == true,
                    onClick = {
                              navController.navigate(navItem.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }
                                  launchSingleTop = true
                                  restoreState = true
                              }
                    },
                    icon = { Icon(imageVector = navItem.icon, contentDescription = null)},
                    label = { Text(text = navItem.label)}
                )
            }
        }

    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name) {
                HomeScreen(viewModel = viewModel, navController = navController)
            }
            composable(route = Screens.SearchScreen.name) {
                SearchScreen()
            }
            composable(route = Screens.CartScreen.name) {
                CartScreen()
            }
            composable(route = Screens.ProfileScreen.name) {
                ProfileScreen(contextProvider = context)
            }
            composable(route = Screens.ProductDetail.name){backStackEntry ->
               ProductDetail(navController = navController) 
            }
        }

    }
 }
