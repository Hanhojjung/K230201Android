package com.example.test17_4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.test17_4.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //추가
    lateinit var file : File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //공유 프리퍼런스 값 불러오기.
        //공유 프리퍼런스 예제 사용해보기
        val pref = getSharedPreferences("inputPref", Context.MODE_PRIVATE)
        // 값을 설정 하는 부분.
//        pref.edit().run {
//            putString("test", "앱별 공유 프리퍼런스 테스트중, 값 부분.")
//            putString("name","이상용, 공유 파일명(이름은 아무거나)은 :inputPref ")
//            commit()
//        }
        // 값을 가져오는 부분.
        val resultStr2 : String? = pref.getString("test","default")
        val resultStr3 : String? = pref.getString("name","default")
        val result3 = resultStr2.toString()
        val result4 = resultStr3.toString()
        Log.d("lsy","test result3 결과 : $result3")
        Log.d("lsy","name result4 결과 : $result4")
        Toast.makeText(this@MainActivity,"${result3}, ${result4}", Toast.LENGTH_SHORT).show()


        //참고 코드 : test17-> MainActivity
        // file 선언 , 뷰 , xml 복사하기.
        //pdf p14 예제 확인.
        // 예제 문서 폴더 위치로 , 정해진 이름의 상수값 경로 확인 해보기
//        val file2: File? = getExternalFilesDir(null) -> 앞에 기본 경로/본인패키지명/files/
        val file2: File? = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        if (file2 != null) {
            Log.d("lsy","getExternalFilesDir의 위치 : ${file2.absolutePath}")
        }

        binding.button1.setOnClickListener {
            //실제 물리 경로에 , 물리 파일을 만드는 작업. 빈 파일.
            file = File(filesDir, "test230522-1.txt")
            // 해당 파일에 쓰기 작업.
            val writeStream: OutputStreamWriter = file.writer()
            // writeStream 객체에 write 함수로 쓰기 작업을 진행.
            writeStream.write("앱별 저장소에 파일 저장 테스트 내용.")
            // 적용한다.
            writeStream.flush()

//            openFileOutput("test.txt", Context.MODE_PRIVATE).use {
//                it.write("hello world!!".toByteArray())
//            }

        }
        binding.button2.setOnClickListener {
            //IO -> Input Output , stream 단어가 없으면 , 문자 기반, (문자열)
            // stream 이 있으면, 바이트 단위로 작업을 한다.
            val readStream: BufferedReader = file.reader().buffered()
            readStream.forEachLine {
                Log.d("lsy", "$it")
            }

//            openFileInput("test.txt").bufferedReader().forEachLine {
//                Log.d("lsy", "$it")
//            }
        }
    }
}