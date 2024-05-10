package com.example.deltagames.model
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Usuario (
    val id: Int,
    val name: String,
    val userEmail: String,
    val cpf: String,
    val password: String
): Parcelable