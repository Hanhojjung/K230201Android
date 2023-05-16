package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //참고하는 코드
        // 1) MainActivity338 2) OneFragment 3) fragment_one.xml 4)activity_main338.xml
        //Fragment 생성 하는 방법, 엑티비티 파일 생성하는 것돠 비슷합니다.
        //Activity 밑에 Fragment를 선택하여 만들면 됩니다.

        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()
        // activity_main338.xml 여기에, 각  프래그먼트들이 보여주느 하나의 틀이다.
        // 프래그먼트들은 부품처럼 교체되어서 보여지게된다.
        transaction.add(R.id.fragment_content, fragment)
        transaction.commit()


    }
}


