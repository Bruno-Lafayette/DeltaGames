package com.example.deltagames.view.cartScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.deltagames.R
import com.example.deltagames.model.Endereco
import com.example.deltagames.model.Pedido
import com.example.deltagames.model.Produto
import com.example.deltagames.model.cart
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.cartScreen.components.CardAddress
import com.example.deltagames.view.cartScreen.components.CardPaymentDetail
import com.example.deltagames.view.cartScreen.components.CardProduct
import com.example.deltagames.view.navigation.Screens
import com.example.deltagames.viewModel.AddressViewModel
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.HomeViewModel
import com.example.deltagames.viewModel.LoginViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CartScreen(
    navController: NavController,
    vmCart: CartViewModel,
    vmUser: LoginViewModel,
    vmHome: HomeViewModel,
    contextProvider: ContextProvider
) {
    var selectedAddress by remember { mutableStateOf(Endereco(0,0,"","",0,"", "","", "" )) }

    val isLoggedIn by vmUser.isActive.observeAsState(false)
    val cartItems by vmCart.cartItems.observeAsState(emptyList())
    val products by vmHome.products.observeAsState(emptyList())
    var productDetails by remember { mutableStateOf<List<Pair<Produto, Int>>>(emptyList()) }
    LaunchedEffect(key1 = cartItems, key2 = products) {
        productDetails = vmCart.listProducts(products, cartItems)
    }
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDate = currentDate.format(formatter)

    Scaffold(
        containerColor = Color.White,
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
                .padding(top = contentPadding.calculateTopPadding())
                .background(Color.White),
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
                            Column (

                            ) {
                                Text(
                                    modifier = Modifier.padding(),
                                    text = "Nenhum produto adicionado",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                AsyncImage(model = "https://freepngimg.com/save/126439-adventure-bmo-time-free-clipart-hq/961x869", contentDescription = "Personagem BMO Meditando")
                            }
                        }
                    } else {
                    Row (modifier = Modifier.height(300.dp)) {
                        LazyColumn {
                            items(productDetails){
                                CardProduct(vmUser = vmUser, product = it, vmCart = vmCart)
                            }
                        }
                    }
                    CardAddress(
                        selectedAddress.nome,
                        AddressViewModel.getInstanceUnique().listAddress.value!!,
                        "Selecione um Endereço",
                        ){
                        selectedAddress = it
                    }
                    CardPaymentDetail(productDetails)
                        if (selectedAddress.endereco_id != 0){
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
                                onClick = {

                                    cart.orderUser = Pedido(
                                        vmUser.user!!.id,
                                        selectedAddress.endereco_id,
                                        1,
                                        formattedDate.toString(),
                                        vmCart.listItens(products, cartItems)
                                    )
                                    navController.navigate(Screens.PaymentScreen.name)
                                }
                            ) {
                                Text(text = "Pagamento")
                            }
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