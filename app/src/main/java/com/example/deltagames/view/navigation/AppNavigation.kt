package com.example.deltagames.view.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.paymentScreen.PaymentScreen
import com.example.deltagames.view.profileScreen.ProfileScreen
import com.example.deltagames.view.searchScreen.SearchScreen
import com.example.deltagames.view.addressScreen.AddAddressScreen
import com.example.deltagames.view.addressScreen.ListAddressScreen
import com.example.deltagames.view.cartScreen.CartScreen
import com.example.deltagames.view.homeScreen.HomeScreen
import com.example.deltagames.view.productDetailScreen.ProductDetail
import com.example.deltagames.view.settingScreen.SettingScreen
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.HomeViewModel
import com.example.deltagames.viewModel.LoginViewModel
import com.example.deltagames.viewModel.SharedProductViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
 fun AppNavigation(viewModel: HomeViewModel, context: ContextProvider ) {

    val sharedProductViewModel: SharedProductViewModel = viewModel()
    val vmLogin = LoginViewModel.getInstanceUnique()
    val navController = rememberNavController()
    val vmCart: CartViewModel = viewModel()
    val userViewModel = remember {
        LoginViewModel.getInstanceUnique()
    }

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
                HomeScreen(viewModel = viewModel, navController = navController, sharedProductViewModel)
            }
            composable(route = Screens.SearchScreen.name) {
                SearchScreen()
            }
            composable(route = Screens.CartScreen.name) {
                CartScreen(navController, vmCart, vmLogin, viewModel, context)
            }
            composable(route = Screens.ProfileScreen.name) {
                ProfileScreen(contextProvider = context, navController = navController, userViewModel)
            }
            composable(route = Screens.ProductDetail.name){backStackEntry ->
               ProductDetail(navController = navController, sharedProductViewModel, context.context , vmCart, vmLogin )
            }
            composable(route = Screens.ListAddressScreen.name){
                ListAddressScreen(navController = navController)
            }
            composable(route = Screens.SettingScreen.name){
                SettingScreen(userViewModel = userViewModel, navController = navController)
            }
            composable(route = Screens.AddAddressScreen.name){
                AddAddressScreen(navController = navController, context = context)
            }
            composable(route = Screens.PaymentScreen.name){
                PaymentScreen(navController, vmLogin, context, vmCart)
            }
        }

    }
 }
