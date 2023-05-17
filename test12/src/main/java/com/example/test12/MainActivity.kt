package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test12.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 적용하기.
        setSupportActionBar(binding.toolbar)

        //임시 데이터 만들기.
        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        //리사이클러 뷰 만드는 재료
        //뷰홀더, 어댑터, 레이아웃 매니저, 적용.
        //클래스 2개 만들고,

        //앱 바라는 뷰에, 툴바, 이미지 뷰, 리사이클러 뷰도
        //참고코드 : MainActivity378

    }
}