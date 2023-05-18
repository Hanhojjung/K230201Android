package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭 시, Detail Activity로 이동
        binding.btn1.setOnClickListener{
            //::class.java, 같은 앱의 액티비티 전달 시, 타입을 명시적 타입(명클)으로 한다.
            val intent = Intent(this@MainActivity,DetailMainActivity::class.java)
            startActivity(intent)
        }
    }
}