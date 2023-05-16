package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain4Binding
import com.example.test11.databinding.Item342Binding

// 참고 코드 -> MainActivity342
//리사이클러뷰를 다른 액티비티에서 재활용 될 예정
class MainActivity4 : AppCompatActivity() {
    //뷰 홀더 만들기 -> Item342Binding 바인딩
    //여기에 항목은 목록의 구성 요소들의 뷰 객체를 모아 두었다.
    // 샘플로 텍스트 뷰 한개만 구성됨. Item342Binding
    class MyViewHolder (val binding: Item342Binding) : RecyclerView.ViewHolder(binding.root)
    //어댑터 만들기
    //데이터 연동 -> 현재 임시 데이터 리스트 9개 값을 -> 해당 뷰 홀더의 아이템 요소의 값으로 사용.
    //나중에, 텍스트, 이미지 등 여러 데이터를 해당 뷰에 할당 작업.
    //보통 데이터는 AIP 서버에서 받아온 값(중간 데이터로 JSON 형식으로 받아서, 필요한 요소만 사용할 예정.)
    //예를 들어, 공공 데이터, 한 아이템의 요소의 값이 10개 있으면, 그 중에서 4개 정도 선택한다.
    //썸네일 이미지 1개, 제목 1개, 위치 1개, 전화번호 1개
    //해당 뷰에 하나씩 재할당 함.

    class MyAdapter(var datas : MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        //필수적으로 재정의 -> 자동완성
        //재정의 한 함수는 다 자동 호출, 순서 상관x
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        //MyViewHolder() -> 정의 , 참고 코드 -> item_342.xml, 리사이클러뷰의 하나의 아이템 구성
            //설계순서 : 큰 것 -> 작은 것, 개발순서 : 작은 것 -> 큰 것(아이템 -> 리스트)
            MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun getItemCount(): Int {
            Log.d("홍길동", "init datas size 크기 : ${datas.size} ")
            return  datas.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            //holder: RecyclerView.ViewHolder -> 형 : 부모 클래스 타입
            //holder 형 변환 -> 자식 클래스 MyViewHolder로 다운캐스팅
            val binding = (holder as MyViewHolder).binding

            //뷰에 데이터를 출력 : 아이템 요소 하나, 어댑터 데이터를 해당 뷰에 연결
            binding.itemData.text = datas[position]

            //뷰에 이벤트 추가 : itemRoot -> RecyclerView그룹(레이아웃)
            binding.itemRoot.setOnClickListener{
                /*Toast.makeText(this@MyAdapter, "메세지 요소 인덱스 : $position ", Toast.LENGTH_SHORT).show()*/
                Log.d("홍길동" , "클릭시 해당 아이템 요소 : $position")

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //임시 데이터
        val datas = mutableListOf<String>()
        for(i in 1..9){
            datas.add("Item $i")
        }

        //설정 부분 -> 뷰홀더, 어댑터, 레이아웃 매니저 -> 아래 클래스 참고

        //설정 적용 부분
        //1.출력을 위한 목록의 틀 : activity_main4.xml에 추가
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //2.틀에 해당 어댑터 붙이기
        binding.recyclerView.adapter = MyAdapter(datas)
        //3.아이템 간의 구분선
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}