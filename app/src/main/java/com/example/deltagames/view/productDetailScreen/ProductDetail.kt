package com.example.deltagames.view.productDetailScreen


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.deltagames.R
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.util.component.HorizontalCounterComponent
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.LoginViewModel
import com.example.deltagames.viewModel.SharedProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(
    navController: NavController,
    sharedProductViewModel: SharedProductViewModel,
    context: Context,
    vmCart: CartViewModel,
    vmUser: LoginViewModel
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val price = sharedProductViewModel.priceStringFormat().first
    val productIsActive = sharedProductViewModel.priceStringFormat().second
    var finalValue by remember { mutableStateOf(1) }
    val isLoggedIn by vmUser.isActive.observeAsState(false)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                title = {
                    Text(
                        "Detalhes do Game",
                        maxLines = 1,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.HomeScreen.name)

                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) {contentPadding ->
        sharedProductViewModel.product?.let {produto ->
            Column (modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding())
                .background(Color.White)){
                AsyncImage(
                    model = produto.IMAGEM_URL,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.CenterStart
                )
                Text(
                    text = produto.PRODUTO_NOME,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
                Row(horizontalArrangement = Arrangement.Absolute.Center) {
                    Text(
                        text = price,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    HorizontalCounterComponent(finalValue){newValue ->
                        finalValue = newValue

                    }
                }
                Text(
                    text = "Descrição do Game",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)

                )
                Text(text = produto.PRODUTO_DESC,
                    modifier = Modifier.padding(8.dp)
                )
                if (isLoggedIn){
                    if (productIsActive){
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
                            onClick = {
                                vmCart.addProductCart(CarrinhoItem(vmUser.user!!.id, produto.PRODUTO_ID, finalValue)) { response ->
                                    println("###############VALOR FINAL##################")
                                    println(finalValue)
                                    response?.let {
                                        showAlertDialog(context, "Sucesso",
                                            it.message
                                        )
                                    }
                                }
                            }
                        ) {
                            Text(text = "Adicionar Carrinho")
                        }
                    }

                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        Text(text = "Para adicionar produto no carrinho necessário fazer Login")
                    }
                }

            }
        }
    }
}
