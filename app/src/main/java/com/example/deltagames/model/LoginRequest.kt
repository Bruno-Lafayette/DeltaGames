package com.example.deltagames.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class LoginRequest (
    val email: String,
    val password: String
): Parcelable