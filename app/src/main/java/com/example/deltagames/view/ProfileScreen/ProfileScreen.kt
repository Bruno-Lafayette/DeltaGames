package com.example.deltagames.view.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.ProfileScreen.components.TabContent
import com.example.deltagames.view.ProfileScreen.components.TabItem
import com.example.deltagames.view.ProfileScreen.components.Tabs
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(contextProvider: ContextProvider) {
    val  list = listOf(TabItem.Login, TabItem.Register)
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState, contextProvider = contextProvider)
    }
}