package com.example.test18_pdtest

import android.app.Application
import com.example.test18.retrofit.INetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application(){
    companion object{
        val serviceKey = "ILEuvHedm5Mts3ZpudoU8a9%2BDdaOwoecKJOjXjU%2B9ojDsxSn8dG%2BRugXbJrL4r4biQE3pvzrt4mCSa1caDW8Vw%3D%3D"
    }
    // MyApplication : Application() 상속을 받아야 함.
    //INetworkService 인터페이스 타입의 변수를 선언
    //Retrofit 타입의 객체를 생성
    //retrofit.create(INetworkService::class.java)
    // 위에서 정의한 인터페이스를 구현한 객체를 반환한 값 가지고 와서
    // networkService에 할당함

    // 주의사항, 사용 여부 -> 매니페스트 파일의 <application 태그 내부에 name으로 설정해서,
    // 해당 앱을 실행할 때, 메모리 상에 등록해서 사용한다.

    // 선언만
    var networkService: INetworkService

    /*현재 접근하기 위한 공공데이터 서버의 API 주소의 예제
    http://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=인증키&numOfRows=10&pageNo=1*/


    val retrofit: Retrofit
    get() = Retrofit.Builder()
        .baseUrl("http://apis.data.go.kr/6260000/FoodService")
//        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init { // 클래스를 생성 또는 사용하면, 할당 작업을 함
        networkService = retrofit.create(INetworkService::class.java)
    }
}