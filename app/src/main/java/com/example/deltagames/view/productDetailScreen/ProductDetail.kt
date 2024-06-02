package com.example.deltagames.view.productDetailScreen


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.graphicsLayer
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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
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

                HorizontalPager(
                    count = produto.IMAGENS_URL.size,
                    contentPadding = PaddingValues(horizontal = 32.dp),) {page ->
                    Card (
                        Modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                val scale =
                                    0.85f + (1f - pageOffset.coerceIn(0f, 1f)) * (1f - 0.85f)
                                val alpha = 0.5f + (1f - pageOffset.coerceIn(0f, 1f)) * (1f - 0.5f)
                                scaleX = scale
                                scaleY = scale
                                this.alpha = alpha
                            }
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        AsyncImage(
                            model = produto.IMAGENS_URL[page],
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillBounds,
                            alignment = Alignment.Center
                        )
                    }
                }
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
                    HorizontalCounterComponent(finalValue, produto.PRODUTO_QTD){newValue ->
                        finalValue = newValue

                    }
                }
                Text(
                    text = "Descrição do Game",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)

                )
                Box(modifier = Modifier
                    .height(60.dp)
                    .verticalScroll(rememberScrollState())){
                    Text(text = produto.PRODUTO_DESC,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                if (isLoggedIn){
                    if (productIsActive){
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
                            onClick = {
                                vmCart.addProductCart(CarrinhoItem(vmUser.user!!.id, produto.PRODUTO_ID, finalValue)) { response ->
                                    response?.let {
                                        showAlertDialog(context, "Sucesso",
                                            it.message
                                        )
                                    }
                                }
                                navController.popBackStack()
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

