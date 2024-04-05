package com.example.deltagames.model
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class Produto(
    val PRODUTO_ID:         Int,
    val PRODUTO_NOME:       String,
    val PRODUTO_DESC:       String,
    val PRODUTO_PRECO:      Double,
    val PRODUTO_DESCONTO:   Double,
    val CATEGORIA_ID:       Int,
    val PRODUTO_ATIVO:      Int,
    val IMAGEM_URL: String

): Parcelable
