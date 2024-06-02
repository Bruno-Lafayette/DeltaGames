package com.example.deltagames.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Pedido (
    var usuario_id: Int,
    var endereco_id: Int,
    var status_id: Int,
    var pedido_data: String,
    var itens: List<Item>
): Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Item (
    var produto_id: Int,
    var item_qtd: Int,
    var item_preco: Double
): Parcelable

object cart {
    var orderUser: Pedido? = null
        get() = field
        set(value) {
            field = value
        }
}