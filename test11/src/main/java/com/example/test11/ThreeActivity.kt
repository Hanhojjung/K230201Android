package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

class ThreeActivity : AppCompatActivity() {

    //(menu)XML에서 마는 메뉴를 해당 엑티비티 화면에 적용하는 코드임
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_three,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
    }
}