package com.example.test18_pdtest_pdtesti_newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test18_pdtest_pdtesti_newsapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
/*    초반 빌드 작업
    1) 뷰바인딩 2)레트로핏 3)gson, gson-convert 4)glide
    test18의 build.gradle 확인
    소스 복사를 원본 : ch18_network 복사*/
lateinit var binding: ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    var mode = "volley"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyFragment= VolleyFragment()
        retrofitFragment= RetrofitFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_content, volleyFragment)
            .commit()
        supportActionBar?.title="Volley Test"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_volley && mode !== "volley"){
            supportFragmentManager.beginTransaction()
            /*여기에 메인 액티비티에 프레임 레이아웃
            전체화면을 하나의 프래그먼트 뷰로 보여주는데
            메뉴 아이템 선택에서, 각 요소를 선택 시
            프래그먼트 화면 전체를 교체해주는 형식*/
                    
                .replace(R.id.activity_content, volleyFragment)
                .commit()
            mode="volley"
            supportActionBar?.title="Volley Test"
        }else if(item.itemId === R.id.menu_retrofit && mode !== "retrofit"){
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, retrofitFragment)
                .commit()
            mode="retrofit"
            supportActionBar?.title="Retrofit Test"
        }
        return super.onOptionsItemSelected(item)
    }
}