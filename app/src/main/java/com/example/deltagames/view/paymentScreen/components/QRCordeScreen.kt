package com.example.deltagames.view.paymentScreen.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deltagames.R
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.cart
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.LoginViewModel
import com.example.deltagames.viewModel.OrderViewModel

@Composable
fun QRCodeScreen(
    navController: NavController,
    contextProvider: ContextProvider,
    cartViewModel: CartViewModel,
    loginViewModel: LoginViewModel
){
    val orderVM = OrderViewModel.getInstanceUnique()
    val context = LocalContext.current
    val code = "AbCDeFGHiJKLmNoPQrSTUvWXyZ1234567890abcdefgHIjklMNOpqrsTUVWXYZ"
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = code, textAlign = TextAlign.Center)

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
            onClick = {
                val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.setPrimaryClip(ClipData.newPlainText("label", code))
                Toast.makeText(context, "Texto copiado", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = "Copiar")
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 28.dp, horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue)),
            onClick = {
                val order = cart.orderUser
                cart.orderUser = null
                if (order != null){
                    orderVM.create(order){
                        cartViewModel.cartItems.value?.forEach {produto->
                            cartViewModel.removeProductCart(CarrinhoItem(loginViewModel.user!!.id, produto.product, produto.qtd)){}
                        }
                        showAlertDialog(contextProvider.context, "Obrigado pela preferência", it!!.message)
                        navController.popBackStack()
                    }
                }
            }
        ) {
            Text(text = "Finalizar Compra")
        }

    }
}
