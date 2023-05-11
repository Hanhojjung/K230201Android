package com.example.test6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    // 코드 작업 공간, 뷰 작업도 가능하지만 가급적 뷰는 xml에서 작업하기
    // 이유 -> 여기에서는 데이터 관련 작업 및 이벤트 핸들러 등 작업하기 위해서, 분리
    // 만약, 한 파일에 뷰까지 더해서, 작업을 하게디면, 가도성이 안좋음, 유지보수도 어려움. 분리
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}