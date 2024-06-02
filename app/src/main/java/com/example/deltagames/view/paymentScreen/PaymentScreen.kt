package com.example.deltagames.view.paymentScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deltagames.util.ContextProvider
import com.example.deltagames.view.paymentScreen.components.FormCreditCardScreen
import com.example.deltagames.view.paymentScreen.components.FormDebitScreen
import com.example.deltagames.view.paymentScreen.components.Payment
import com.example.deltagames.view.paymentScreen.components.QRCodeScreen
import com.example.deltagames.viewModel.CartViewModel
import com.example.deltagames.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    login: LoginViewModel,
    contextProvider: ContextProvider,
    cartViewModel: CartViewModel
){

    val isLoggedIn by login.isActive.observeAsState(false)
    var methodPaymentSelected by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val methodPayment: List<Payment> = listOf(Payment.credito, Payment.debito, Payment.pix)

    if (isLoggedIn){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
                    title = {
                        Text(
                            "Forma de Pagamento",
                            maxLines = 1,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()

                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
        ) {contentPadding ->
            Column (
                modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
            ){
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.padding(top = contentPadding.calculateTopPadding())
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = methodPaymentSelected,
                        onValueChange = {},
                        label = { Text(text = "Selecione a forma de pagamento") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = OutlinedTextFieldDefaults.colors(),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        methodPayment.forEach { option: Payment ->
                            DropdownMenuItem(
                                text = { Text(text = option.toString()) },
                                onClick = {
                                    expanded = false
                                    methodPaymentSelected = option.toString()
                                }
                            )
                        }
                    }
                }
                when (methodPaymentSelected) {
                    Payment.credito.toString()  -> FormCreditCardScreen(navController = navController, contextProvider = contextProvider, cartViewModel, login)
                    Payment.debito.toString()   -> FormDebitScreen(navController = navController, contextProvider = contextProvider, cartViewModel, login)
                    Payment.pix.toString()      -> QRCodeScreen(navController = navController, contextProvider = contextProvider, cartViewModel, login)
                }
            }
        }
    } else {
        navController.popBackStack()
    }
}