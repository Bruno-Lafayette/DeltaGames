package com.example.deltagames.view.cartScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.Produto
import com.example.deltagames.util.component.VerticalCounterComponent
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.LoginViewModel


@Composable
fun CardProduct(
    vmUser: LoginViewModel,
    product: Pair<Produto, Int>,
    vmCart: CartViewModel,
){

    fun price(price: Double?, desc: Double?): String {
        return String.format("%.2f", (price ?: 0.0) - (desc ?: 0.0))
    }

   Card(
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(2.dp, shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White
    ) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
    ) {
        AsyncImage(
            model = product.first.IMAGENS_URL.first(),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))

        )
        Column(
            modifier = Modifier
                .width(180.dp)
                .padding(top = 8.dp)
        ) {
            Text(
                text = product.first.PRODUTO_NOME,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "R$${price(product.first.PRODUTO_PRECO, product.first.PRODUTO_DESCONTO)}",
                color = Color.Gray,
            )
            IconButton(onClick = {
                vmCart.removeProductCart(CarrinhoItem(vmUser.user!!.id, product.first.PRODUTO_ID, product.second)) {
                    println(it?.message)
                }
            }) {
                Icon(imageVector = Icons.TwoTone.Delete, contentDescription = "", tint = Color.Red)

            }
        }
        VerticalCounterComponent(product.second, product.first.PRODUTO_QTD, vmCart,product.first.PRODUTO_ID)
    }
    }
}
