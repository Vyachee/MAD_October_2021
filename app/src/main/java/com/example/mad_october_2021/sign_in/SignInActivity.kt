package com.example.mad_october_2021.sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_october_2021.R
import com.example.mad_october_2021.databinding.ActivitySignInBinding
import com.example.mad_october_2021.main_screen.MainScreenActivity

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener {
            startActivity(Intent(baseContext, MainScreenActivity::class.java))
            finish()
        }
    }
}