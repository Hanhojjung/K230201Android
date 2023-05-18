package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityMain443Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
//            // 비동기 적용하기 전에 문제점 제시부터
//            var sum = 0L
//            var time = measureTimeMillis {
//                for(i in 1..10_000_000_000){
//                    sum += i
//                }
//            }
//            Log.d("lsy","time : $time")
//            binding.resultView.text = "sum : $sum"

         /*   //방법1 핸들러 스레드로 비동기 처리

            val handler=object: Handler(){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    binding.resultView.text = "sum : ${msg.arg1}"
                }
            }

            thread {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..7_000_000_000) {
                        sum += i
                    }
                    val message = Message()
                    message.arg1=sum.toInt()
                    handler.sendMessage(message)
                }
                Log.d("lsy", "time : $time")
            }*/

            // 방법2 코루틴으로 비동기 처리, 결과는 거의 비슷
            // 차이점 권고 및 앞으로 계속 지원 등으로, 코루틴 이용
            // 이미 많은 api 등에서 채택에서 서비스에 적용중.
            // 그래서,api 등을 이용 시 비동기 처리로 인해서 빠르게 처리

            val channel = Channel<Int>()

            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..7_000_000_000) {
                        sum += i
                    }
                }
                Log.d("lsy", "time : $time")
                channel.send(sum.toInt())
            }

            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }

           /* 코루틴 메모
                    코루틴(함께 처리한다 = 비동기 처리방식)
            스코프 개념?
            성격이 같은 코루틴을 묶어 주는 역할(클래스 개념)
            예를들어)
            하나의 스코프에 여러 개의 코루틴을 구동 가능.
            한 애플리케이션에 여러 스코프를 만들수 있다.
            -> 1: N = 스코프 : 코루틴
            -> 1: N = 애플리케이션 : 스코프

            3가지 정도의 스코프의 디스패처(관제탑) 대해서
                    backgroundScope 와, mainScope 만들면서, 지정한 디스패처 3가지.
            1) Dispatchers.Main
            액티비티의 메인 스레드에서 동작하는 코루틴
            2) Dispatchers.IO
            파일을 읽거나 쓰기 또는 네트워크 작업 등에 최적화
                    3) Dispatchers.Default
            CPU 를 많이 사용하는 작업을 백그라운드에서 실행.

            결과
            Dispatchers.Main으로 만든 스코프에서 실행한 코루틴은 메인스레드에서 동작하고,
            따라서, UI 변경 가능. 중요함.
            하지만, 메인 스레드는 사용자 이벤트(에디트 텍스트부분 입력부분)를
            처리하는 곳이므로 이 코루틴이 빨리 끝나는 작업을
            맡기는 것이 좋다.

            오래 걸리는 작업 CPU 연산이 많이 필요한 부분은 담당을
            Dispatchers.Default에서 수행을 했다.
            그 결과를 Dispatchers.Main으로 전달함.

            Channel을 이용해서 해당 클래스에 코루틴의 값을 전달 할수있게 제공해줌.
            Channel은 자료구조상 Queue 큐 알고리즘과 비슷함.*/
        }
    }
}