package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityDetailMainBinding

class DetailMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}