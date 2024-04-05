package com.example.deltagames.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Categoria(
    val CATEGORIA_ID: Int,
    val CATEGORIA_NOME: String,
): Parcelable
