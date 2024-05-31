package com.example.deltagames.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Endereco(
    val endereco_id:    Int,
    val usuario_id:     Int,
    val nome:           String,
    val logradouro:     String,
    val numero:         Int,
    val complemento:    String,
    val cep:            String,
    val localidade:     String,
    val uf:             String
): Parcelable {
    constructor(usuario_id: Int) : this(
        endereco_id = 0,
        usuario_id = usuario_id,
        nome = "",
        logradouro = "",
        numero = 0,
        complemento = "",
        cep = "",
        localidade = "",
        uf = ""
    )
}
