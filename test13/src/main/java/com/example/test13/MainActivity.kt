package com.example.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // onCreate 최초 한번 실행 시, binding 늦게 할당이 되어서, 마치 전역처럼 사용 가능.
    // onCreate 함수 내부 뿐만 아니라, 다른 함수 내부에서도 사용가능.
    lateinit var binding: ActivityMainBinding

    // 생명주기 onCreate 최초 한번 실행
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //버튼 클릭 시, Detail Activity로 이동
        binding.btn1.setOnClickListener{
            //::class.java, 같은 앱의 액티비티 전달 시, 타입을 명시적 타입(명클)으로 한다.
            val intent = Intent(this@MainActivity,DetailMainActivity::class.java)

            //문자열, 정수를 설정해서, 보내는 데이터 설정/
            intent.putExtra("data1","이상용")
            intent.putExtra("data2",518)

            //단순 화면 이동만 하는 함수
//            startActivity(intent)
            //후처리를 하는 함수를 사용해야 함.
            startActivityForResult(intent,10)
        }
    }

    // onCreate 밖에서, 후처리 방법 중 방법1, 가급적 사용 자제, 이유는 이후 버전이 나와서.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 10 && resultCode == Activity.RESULT_OK){
            val result = data?.getStringExtra("result")
            Log.d("lsy","result 값 조회 : $result")
            binding.resultMainViewText.text = result
        }
    }
}