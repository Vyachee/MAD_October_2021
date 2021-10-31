package com.example.mad_october_2021.main_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mad_october_2021.R
import com.example.mad_october_2021.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}