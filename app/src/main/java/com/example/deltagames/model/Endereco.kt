package com.example.deltagames.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Endereco(
    val endereco_id: Int,
    val usuario_id: Int,
    val nome: String,
    val logradouro: String,
    val numero: Int,
    val complemento: String,
    val cep: Int,
    val cidade: String,
    val estado: String
): Parcelable
