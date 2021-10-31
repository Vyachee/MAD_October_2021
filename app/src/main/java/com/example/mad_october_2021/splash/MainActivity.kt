package com.example.mad_october_2021.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.example.mad_october_2021.R
import com.example.mad_october_2021.auth_select.SelectAuthActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goNext()
    }

    private fun goNext() {
        GlobalScope.launch {
//            SystemClock.sleep(2000)
            SystemClock.sleep(100)
            runOnUiThread {
                val intent = Intent(baseContext, SelectAuthActivity::class.java)
                startActivity(intent)
            }
        }
    }
}