package com.example.test6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.ImageGetter
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.test6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { //AppCompoatActivity 부모 클래스로부터 상속 받음
    // 코드 작업 공간, 뷰 작업도 가능하지만 가급적 뷰는 xml에서 작업하기
    // 이유 -> 여기에서는 데이터 관련 작업 및 이벤트 핸들러 등 작업하기 위해서, 분리
    // 만약, 한 파일에 뷰까지 더해서, 작업을 하게디면, 가도성이 안좋음, 유지보수도 어려움. 분리
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //savedInstanceState -> 저장소 개념. 16장에서 더 설명. 객체에 저장
        // res -> layout -? xml 파일을 불러와서, 실제로 화면 출력
        // 리소스 R.java 파일이라는 곳에 상수 값으로 저장되는데, 여기서 불러옵니다.
        // 그래서, 코드에서 리소스의 갑을 불러올 때 항상, R.layout 등으로 시작해서 불러옵니다.
        // 화면을 출력하겠다. 레이아웃 7장 더 설명.
        // 뷰그룹 : 뷰(텍스트, 이미지, 인풋, 체크박스, 라디오..)를 모아주는 역할
//        setContentView(R.layout.activity_main)

        //뷰 바인딩 기술을 통해서, 뷰들을 특정 바인딩이라는 객체에 모두 모아서,
        //접근을 쉽게 해주는 기술.
        //사용하기전에 항상, build.gradle 파일에 설정 후, sync now 적용해서 사용해야함.

        //이 문법은 자동으로 생성된 바인딩 파일을 inflate 함수와, 인자는 : laoutInflater는 고정
        //setContentView -> 화면에 그리기
        //binding 변수에, 모두 뷰가 다 들어가 있음.
        //결론, 여기서 필요한 뷰를 꺼내 사용한다.
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button1 = findViewById<Button>(R.id.btn1)
        val img1 = findViewById<ImageView>(R.id.img1)
        var status = 0

        //button1을 뷰바인딩 기법으로 접근
        button1.setOnClickListener {
            if (status == 0) {
                binding.img1.visibility = View.INVISIBLE
                status = 1
            } else {
                binding.img1.visibility = View.VISIBLE
                status = 0
            }
        }

//        val button1 = findViewById<Button>(R.id.btn1)
//        val img1 = findViewById<ImageView>(R.id.img1)
//        var status = 0

        //button1 눌러서 -> img show/hide
//        button1.setOnClickListener {
//            if (status == 0) {
//                img1.visibility = View.INVISIBLE
//                status = 1
//            } else {
//                img1.visibility = View.VISIBLE
//                status = 0
//            }
//        }
    }
}