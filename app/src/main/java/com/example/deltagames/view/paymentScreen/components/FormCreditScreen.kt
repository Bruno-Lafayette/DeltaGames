package com.example.deltagames.view.paymentScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deltagames.model.CarrinhoItem
import com.example.deltagames.model.cart
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.util.component.showAlertDialog
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.LoginViewModel
import com.example.deltagames.viewModel.OrderViewModel

@Composable
fun FormCreditCardScreen(
    navController: NavController,
    contextProvider: ContextProvider,
    cartViewModel: CartViewModel,
    loginViewModel: LoginViewModel
) {

    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var cardHolderName by remember { mutableStateOf("") }
    val orderVM = OrderViewModel.getInstanceUnique()


    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            label = { Text("Número do Cartão") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = expirationDate,
            onValueChange = { expirationDate = it },
            label = { Text("Data de Validade") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cvv,
            onValueChange = { cvv = it },
            label = { Text("CVV") },
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = cardHolderName,
            onValueChange = { cardHolderName = it },
            label = { Text("Nome do Titular do Cartão") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
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

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text("Enviar")
        }
    }
}
