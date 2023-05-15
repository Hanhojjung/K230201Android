package com.example.test10

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.test10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //방법1
        val toast = Toast.makeText(this,"메세지 내용",Toast.LENGTH_SHORT)
        binding.btn1.setOnClickListener{
            //toast.show()

            //방법2
            Toast.makeText(this,"토스트 출력 방법2",Toast.LENGTH_SHORT).show()

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