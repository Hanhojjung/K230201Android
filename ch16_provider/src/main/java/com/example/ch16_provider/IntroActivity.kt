package com.example.ch16_provider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler().postDelayed({
            val intent = Intent(this@IntroActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}