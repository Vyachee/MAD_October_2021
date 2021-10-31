package com.example.mad_october_2021.common

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException

class ApiHelper {
    private val baseUrl = "http://45.144.179.101/scare-me/api/mobile/v1"
    private val client = OkHttpClient()

    fun signUp(
        email: String,
        password: String,
        onResponseCallback: OnResponseCallback? = null,
        onFailureCallback: OnFailureCallback? = null
    ) {

        val body = JSONObject()
        body.put("email", email)
        body.put("password", password)


        val request = Request.Builder()
            .url("$baseUrl/auth/register")
            .post(RequestBody.Companion.create("application/json".toMediaTypeOrNull(), body.toString()))
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailureCallback?.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                onResponseCallback?.onResponse(response)
            }

        })

    }
}

interface OnResponseCallback {
    fun onResponse(response: Response)
}

interface OnFailureCallback {
    fun onFailure(e: IOException)
}