package com.example.mad_october_2021.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_october_2021.common.*
import com.example.mad_october_2021.databinding.ActivitySignUpBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

    }

    private fun signUp() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val rPassword = binding.etPasswordRepeat.text.toString()

        val errors = validateFields(email, password, rPassword)
        if (errors.isNotEmpty()) {
           DialogHelper(this).showDialog("There is errors!", errors.first())
            return
        }

        ApiHelper().signUp(email, password, onResponseCallback = object: OnResponseCallback {
            override fun onResponse(response: Response) {
                val stringJson = response.body?.string()
                try {
                    val json = JSONObject(stringJson)

                    if(json.has("message")) {
                        runOnUiThread {
                            DialogHelper(this@SignUpActivity)
                                .showDialog("There is error!", json.get("message").toString())
                        }
                        return
                    }

                    val token = Gson().fromJson(stringJson, Token::class.java)
                    CacheHelper(this@SignUpActivity).saveToken(token)
                    runOnUiThread {
                        DialogHelper(this@SignUpActivity)
                            .showDialog("Success!", "You signed up successfully!")
                    }
                }   catch (e: Exception) {
                    runOnUiThread {
                        DialogHelper(this@SignUpActivity)
                            .showDialog("Oops!", "Something went wrong. Try again later.")
                    }
                }

            }
        })


    }

    private fun initListeners() {
        binding.tvSignUp.setOnClickListener {
            signUp()
        }
    }


    private fun validateFields(email: String, password: String, rPassword: String): List<String> {
        val errors = mutableListOf<String>()
        if (email.isEmpty() || password.isEmpty() || rPassword.isEmpty()) {
            errors.add("All fields must be filled!")
            return errors
        }

        if (!email.contains('@') || !email.contains('.')) {
            errors.add("Email is not valid!")
            return errors
        }

        if (password != rPassword) {
            errors.add("Passwords must be equal!")
            return errors
        }

        return errors
    }
}