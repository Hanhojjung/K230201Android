package com.example.test10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.test10.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //알림 채널을 만들고 -> 빌더에 인자로 넣고 -> Notification 객체 만들고 -> notify()에 넣기
        binding.btnChannel.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder : NotificationCompat.Builder

        // 26버전 기준으로, 26 이상이면 A메서드 형식, 26버전 미만이면 , B 메서드 형식
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // 채널을 만드는 작업.
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                // 채널 옵션 설정.
                channel.description = "My Channel 테스트 중. 230515"
                // 앱을 만들면, 기본 앱 아이콘 -> 알림 확인을 하지 않은 정보의 갯수가 숫자로 표시가 된다.
                // 우리가 설정하는 것이 아니라, 안드로이드 시스템에서 자동 설정 -> 확인 시 숫자 사라짐
                channel.setShowBadge(true)
                // 소리 관련.
                val soundUri : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttr = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                //소리 설정 적용
                channel.setSound(soundUri,audioAttr)
                //알림 시 LED 깜박임
                channel.enableLights(true)
                channel.lightColor = Color.RED
                // 알림 시 진동 설정 on
                channel.enableVibration(true)
                // 진동 설정 시 주기 옵션
                channel.vibrationPattern = longArrayOf(100,200,100,200)

                // 만든 채널을 NotificationManager에 등록
                manager.createNotificationChannel(channel)

                // 채널을 이용해서 builder 생성
                builder = NotificationCompat.Builder(this@MainActivity2,channelId)

            } else{
                builder = NotificationCompat.Builder(this@MainActivity2)
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("임시 제목")
            builder.setContentText("전달할 임시 메시지 내용.")

            // intent -> 시스템에 메시지를 전달하는 도구
            // 역할 : 1) 화면 간의 전환, 2) 화면 간의 데이터 전달.
            // 현재 1번 용도로 사용. 현재 화면에서 -> DetailActivity로 전달해 주세요.
            val intent = Intent(this@MainActivity2,DetailActivity::class.java)


            // 기존의 intent에 옵션을 부가.
            // 1) 요청 번호(10)
            // 2) 깃발을 이용해서 상태를 표기
            val pendingIntent =
                PendingIntent.getActivity(this@MainActivity2,10,intent,
                PendingIntent.FLAG_IMMUTABLE)

            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)

            // notify 메서드에 인자 값으로 Notification 타입 객체 할당
            manager.notify(11,builder.build())
        }

    }
}