package com.example.deltagames.view.paymentScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
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
fun FormDebitScreen(
    navController: NavController,
    contextProvider: ContextProvider,
    cartViewModel: CartViewModel,
    loginViewModel: LoginViewModel
) {

    var selectedBank by remember { mutableStateOf("") }
    var agency by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var accountDigit by remember { mutableStateOf("") }
    var accountHolderName by remember { mutableStateOf("") }
    val banks = listOf("Bradesco", "Itaú", "Santander", "Nubank", "PagBank")
    val orderVM = OrderViewModel.getInstanceUnique()



    Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Dropdown para selecionar o banco
            DropdownMenu(
                expanded = false, // Modifique o estado conforme necessário
                onDismissRequest = { /* Não faz nada */ }
            ) {
                banks.forEach { bank ->
                    DropdownMenuItem(text = { Text(text = bank) }, onClick = {
                        selectedBank = bank
                    })
                }
            }
            OutlinedTextField(
                value = agency,
                onValueChange = { agency = it },
                label = { Text("Agência") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = accountNumber,
                onValueChange = { accountNumber = it },
                label = { Text("Número da Conta") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = accountDigit,
                onValueChange = { accountDigit = it },
                label = { Text("Dígito da Conta") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )
        }


        // Campo para o nome do titular da conta
            OutlinedTextField(
                value = accountHolderName,
                onValueChange = { accountHolderName = it },
                label = { Text("Nome do Titular da Conta") },
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
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.dark_blue_delta_games)),
            ) {
                Text("Enviar")
            }
        }
    }
