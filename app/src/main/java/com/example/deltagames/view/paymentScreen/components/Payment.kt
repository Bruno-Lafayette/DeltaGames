package com.example.deltagames.view.paymentScreen.components

enum class Payment {
    credito,
    debito,
    pix;
    override fun toString(): String {
        return when (this) {
            credito -> "Crédito"
            debito -> "Débito"
            pix -> "Pix"
        }
    }
}