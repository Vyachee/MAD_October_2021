package com.example.mad_october_2021.common

import android.app.AlertDialog
import android.content.Context

class DialogHelper(private val context: Context) {
    fun showDialog(title: String, text: String, okayCallback: ButtonCallback? = null) {
        val dialog = AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(text)
            .setNegativeButton("Okay") { _, _ -> okayCallback }
            .create()

        dialog.show()
    }
}

interface ButtonCallback {
    fun okayCallback()
}