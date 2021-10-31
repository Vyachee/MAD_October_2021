package com.example.mad_october_2021.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson

class CacheHelper(private val context: Context) {

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences("userData", MODE_PRIVATE)

    fun saveToken(token: Token) {
        val stringToken = Gson().toJson(token)
        sharedPrefs.edit().putString("token", stringToken).apply()
    }

    fun getToken(): Token? {
        val stringToken = sharedPrefs.getString("token", null) ?: return null
        return Gson().fromJson(stringToken, Token::class.java)
    }
}