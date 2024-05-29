package com.example.deltagames.util.component

import android.app.AlertDialog
import android.content.Context


fun showAlertDialog(context: Context, title: String, message: String) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
        .setMessage(message)
    builder.setPositiveButton("OK") { dialog, _ ->
        dialog.dismiss()
    }
    val alertDialog = builder.create()
    alertDialog.show()
}