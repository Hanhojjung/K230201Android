

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding = ActivityMainBinding
    /*공공데이터 API
    부산맛집 정보 서비스
    가입 후, 로그인, 해당 요청 정보에서, API 키 발급 신청
    하루만 사용가능한 키 바로 발급됨
    API 문서 다운로드 받기
    참고할 샘플 코드 : tett18: requres.in
    1)뷰 바인딩 2) 레트로핏 3) gson 4) gson-converter 5)Glide
    test18있는 전체 코드(MainActivity3)를 다 복사
    변경사항
    1)모델 클래스(리스트 포함)
    2)baseUri 주소 및 서버에 전달하는 파라미터 확인
    3)인터페이스에 정의된 함수의 매개변수 확인
    4)뷰의 틀은 그대로 재사용 -> 받아온 정보 중, 보여줄 정보를 선택해서 바인딩 작업
    예)맛집의 제목, 맛집의 썸네일 이미지 URL 주소*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        val userListCall = networkService.doGetUserList("ILEuvHedm5Mts3ZpudoU8a9%2BDdaOwoecKJOjXjU%2B9ojDsxSn8dG%2BRugXbJrL4r4biQE3pvzrt4mCSa1caDW8Vw%3D%3D",10,1,"JSON")
        Log.d("kkang", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<UserListModel> {
            override fun onResponse(
                call: Call<UserListModel>,
                response: Response<UserListModel>
            ) {

                val userList = response.body()
                Log.d("lsy","Test18 userList item 값 : ${userList?.item}")

                //.......................................

                binding.recyclerView.adapter = MyAdapter(this@MainActivity, userList?.item)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )

/*                binding.pageView.text = userList?.page
                binding.totalView.text = userList?.total*/
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })

        binding.testButton.setOnClickListener {
//                val call: Call<UserModel> =
//                    networkService.test1()//https://reqres.in/users/list?sort=desc
//
//                val call: Call<UserModel> =
//                    networkService.test2("10", "kkang")//https://reqres.in/group/10/users/kkang
//
//                val call: Call<UserModel> = networkService.test3(
//                    "age",
//                    "kkang"
//                )//https://reqres.in/group/users?sort=age&name=kkang
//
//                val call: Call<UserModel> = networkService.test4(
//                    mapOf<String, String>("one" to "hello", "two" to "world"),
//                    "kkang"
//                )//https://reqres.in/group/users?one=hello&two=world&name=kkang
//
//                val call: Call<UserModel> = networkService.test5(
//                    UserModel(
//                        id = "1",
//                        firstName = "gildong",
//                        lastName = "hong",
//                        avatar = "some url"
//                    ),
//                    "kkang"
//                )//https://reqres.in/group/users?name=kkang
//
//                val call: Call<UserModel> = networkService.test6(
//                    "gildong 길동",
//                    "hong 홍",
//                    "kkang"
//                )//https://reqres.in/user/edit?name=kkang
//
//                val list: MutableList<String> = ArrayList()
//                list.add("홍길동")
//                list.add("류현진")
//                val call = networkService.test7(list)
//
//                val call = networkService.test9(
//                    "http://www.google.com",
//                    "kkang"
//                )//http://www.google.com/?name=kkang
//
//                Log.d("kkang", "url:" + call.request().url().toString())

        }

    }
}


