package com.example.deltagames.view.cartScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deltagames.R
import com.example.deltagames.model.Produto
import com.example.deltagames.view.cartScreen.components.CardAddress
import com.example.deltagames.view.cartScreen.components.CardPaymentDetail
import com.example.deltagames.view.cartScreen.components.CardProduct
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.HomeViewModel
import com.example.deltagames.viewModel.LoginViewModel


@Composable
fun CartScreen(
    vmCart: CartViewModel,
    vmUser: LoginViewModel,
    vmHome: HomeViewModel
) {
    val isLoggedIn by vmUser.isActive.observeAsState(false)
    val cartItems by vmCart.cartItems.observeAsState(emptyList())
    val products by vmHome.products.observeAsState(emptyList())
    var productDetails by remember { mutableStateOf<List<Pair<Produto, Int>>>(emptyList()) }
    LaunchedEffect(key1 = cartItems, key2 = products) {
        productDetails = vmCart.listProducts(products, cartItems)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                title = {
                    Text(
                        text = "Carrinho",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            )
        }
    ){contentPadding ->
        Box(
            modifier = Modifier
                .padding(top = contentPadding.calculateTopPadding()),
        ) {
            if (isLoggedIn){
                vmCart.fechProductsCart()
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    if (productDetails.isEmpty()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ){
                            Text(modifier = Modifier.padding(),text = "Nenhum produto ainda foi adicionado")
                        }
                    } else {
                    Row (modifier = Modifier.height(300.dp)) {
                        LazyColumn {
                            items(productDetails){
                                CardProduct(vmUser = vmUser, product = it, vmCart = vmCart)
                            }
                        }
                    }
                    CardAddress()
                    CardPaymentDetail(productDetails)
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
                        onClick = {

                        }
                    ) {
                        Text(text = "Pagamento")
                    }
                    }
                }
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(modifier = Modifier.padding(),text = "Faça o login para listar os produtos")
                }
            }
        }
    }


}