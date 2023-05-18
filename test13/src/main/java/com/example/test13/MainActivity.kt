package com.example.test13

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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

        // 인텐트 필터 정보의 속성 예제 확인해보기
        // 버튼을 사용해서, 해당 인텐트 정보를 호출 연습
        binding.btn4.setOnClickListener {
            val intent = Intent()
            // 여기의 값은 개발자가 아무거나 입력해도 상관없음
            //ACTION_EDIT 정의가 된 게 없음
            //변경 : Intent.ACTION_VIEW : 등록된 액션으로 변경
            intent.action = "ACTION_EDIT"
//            intent.action = Intent.ACTION_VIEW
//            intent.data = Uri.parse("http://google.com")
            // 인텐트 setpackage 잠시 보류.
            startActivity(intent)

            //지도 보는 속성 예제.
            // 지도 맵에 여러 개이면, 지도 앱 목록 리스트가 나와서, 그 중에서 선택하면
            /*val intent = Intent(Intent.ACTION_VIEW,Uri.parse("geo:37.7749.127.4194"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)*/
        }

        //설정. 순서1
        val requestLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            //호출 후 특정 데이터 값 불러오기 -> it
            val resultData = it.data?.getStringExtra("result2")
            binding.result2MainViewText.text = resultData
        }

        //버튼 2번, ActivityResultLauncher 라는 타입으로 진행함. 권장
        binding.btn3.setOnClickListener {
            //::class.java, 같은 앱의 액티비티 전달 시, 타입을 명시적 타입(명클)으로 한다.
            val intent = Intent(this@MainActivity,DetailMainActivity::class.java)

            //문자열, 정수를 설정해서, 보내는 데이터 설정/
            intent.putExtra("data1","후처리 방법2")
            intent.putExtra("data2",777)

            //호출하는 함수 형식만 계속 변경중.
            requestLauncher.launch(intent)

        }

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