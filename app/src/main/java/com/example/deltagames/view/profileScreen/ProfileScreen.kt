package com.example.deltagames.view.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.deltagames.R
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.view.profileScreen.components.TabContent
import com.example.deltagames.view.profileScreen.components.TabItem
import com.example.deltagames.view.profileScreen.components.Tabs
import com.example.deltagames.viewModel.LoginViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(contextProvider: ContextProvider, navController: NavHostController, userViewModel: LoginViewModel) {
    val  list = listOf(TabItem.Login, TabItem.Register)
    val pagerState = rememberPagerState()
    val isLoggedIn by userViewModel.isActive.observeAsState(false)
    if (isLoggedIn){
        navController.navigate(Screens.SettingScreen.name)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.dark_blue_delta_games))
        ) {
            Tabs(tabs = list, pagerState = pagerState)
            TabContent(tabs = list, pagerState = pagerState, contextProvider = contextProvider)
        }
    }

}