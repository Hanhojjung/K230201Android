package com.example.test17

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test17.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    //참고 코드 -> ch17_database -> 할일 목록 만들기 , 할일 내용을 데이터베이스 저장,
    // 입력 뷰, 출력 뷰도 있어서 이걸 확인.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    class DBTest(context: Context):SQLiteOpenHelper(context,"testDB",null,1){
        override fun onCreate(db: SQLiteDatabase?) {
            if (db != null) {
                db.execSQL("create table USER_TB (" +
                        "id integer primary key autoincrement,"+
                        "name not null ,count integer)")
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }

    }
}