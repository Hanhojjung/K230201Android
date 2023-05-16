package com.example.test10

import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.test10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    fun  showTest() {
        val toast = Toast.makeText(this,"메세지 내용",Toast.LENGTH_SHORT)
        toast.addCallback(
            object : Toast.Callback() {
                override fun onToastHidden() {
                    super.onToastHidden()
                    Log.d("lsy", "toast hidden, 숨겨진 후 추가 기능 동작. ")
                }

                override fun onToastShown() {
                    super.onToastShown()
                    Log.d("lsy", "toast shown, 보여진 후 추가 기능 동작. ")
                }
            }
        )
        toast.show()
    }
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //날짜 다이얼로그 띄우기, 출력은 콘솔 또는 토스트 메시지
        binding.btnDate.setOnClickListener{
            //DatePickerDialog(this, 리스너, 년도, 월, 일).show
            DatePickerDialog(this,object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    Log.d("lsy","year(년도): $year, month: ${month+1}, dayOfMonth : $dayOfMonth")
                    //토스트에 해당 날짜 띄워보기.
                    Toast.makeText(this@MainActivity,"year(년도): $year, month: ${month+1}, dayOfMonth : $dayOfMonth"
                        ,Toast.LENGTH_SHORT).show()
                }
            }, 2020,5,15).show()
        }

        // 시간을 띄우는 버튼 UI 추가, 해당 시간을 출력하는 1)Log.d 2_토스트 메시지에도 출력하기

        //방법1
//        val toast = Toast.makeText(this,"메세지 내용",Toast.LENGTH_SHORT)


        binding.btn1.setOnClickListener{
            //toast.show()

            //방법2
            //Toast.makeText(this,"토스트 출력 방법2",Toast.LENGTH_SHORT).show()

            //옵션, 토스크 메시지가 보여지거나, 사라졌을 경우에 추가 기능을 확인중.
            showTest()
        }



//        val requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//            )
//        { isGranted ->
//            if (isGranted)
//            {
//                Log.d("lsy", "승인됨.")
//            } else
//            {
//                Log.d("lsy", "승인 안됨.")
//            }
//        }
//
//        val status = ContextCompat.checkSelfPermission(this,"android.permission.ACCESS_FINE_LOCATION")
//        if(status == PackageManager.PERMISSION_GRANTED)
//        {
//            Log.d("lsy", "status 승인 됨2.")
//        }
//        else{
//            requestPermissionLauncher.launch("android.permission.ACCESS_FINE_LOCATION")
//        }
    }

}