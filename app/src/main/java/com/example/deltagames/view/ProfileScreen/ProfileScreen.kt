package com.example.deltagames.view.ProfileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.ProfileScreen.components.TabContent
import com.example.deltagames.view.ProfileScreen.components.TabItem
import com.example.deltagames.view.ProfileScreen.components.Tabs
import com.example.deltagames.viewModel.LoginViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProfileScreen(contextProvider: ContextProvider) {
    val  list = listOf(TabItem.Login, TabItem.Register)
    val pagerState = rememberPagerState()
    val userViewModel = remember {
        LoginViewModel.getInstanceUnique()
    }
    val isLoggedIn by userViewModel.isActive.observeAsState(false)
    if (isLoggedIn){
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp,
                    title = {
                        Text(
                            text = "Perfil",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            userViewModel.isActive.postValue(false)
                            userViewModel.user = null
                        }) {
                            Icon(
                                imageVector = Icons.Rounded.ExitToApp,
                                contentDescription = "Icone de sair"
                            )
                        }
                    }
                )
            }
        ) { contentPadding ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = contentPadding.calculateTopPadding()).padding(16.dp),
            ) {
                Text(text = "Usuario: ${userViewModel.user!!.name}")
                Text(text = "Email: ${userViewModel.user!!.userEmail}")
                Text(text = "CPF: ${userViewModel.user!!.cpf}")
            }

        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Tabs(tabs = list, pagerState = pagerState)
            TabContent(tabs = list, pagerState = pagerState, contextProvider = contextProvider)
        }
    }

}