package com.example.test16_2multiimg

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.test16_2multiimg.databinding.ActivityMainBinding
import com.example.test16_2multiimg.databinding.MultiImageItemBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var list = ArrayList<Uri>()
    var adapter = MultiImageAdapter(list,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            list.clear()
            if(it.data?.clipData != null){
                val count = it.data!!.clipData?.itemCount
                if (count != null) {
                    if(count > 10) {
                        Toast.makeText(this@MainActivity,"사진 선택은 10장까지만 가능",Toast.LENGTH_SHORT).show()
                    }
                }
                for (i in 0 until count!!){
                    val imageUri = it.data!!.clipData?.getItemAt(i)?.uri
                    if (imageUri != null) {
                        list.add(imageUri)
                    }
                }
            } else {
                it.data.let {
                    uri ->
                    val imageUri : Uri? = it.data?.data
                    if(imageUri != null){
                        list.add(imageUri)
                    }
                }
            }
            adapter.notifyDataSetChanged()
        }

        binding.getImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
            intent.setType("image/*")
            intent.action = Intent.ACTION_GET_CONTENT

            requestGalleryLauncher.launch(intent)
        }
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


    }

    class ViewHolder(val binnding: MultiImageItemBinding) : RecyclerView.ViewHolder(binnding.root)

    class MultiImageAdapter(val items:ArrayList<Uri>,val context:Context) :
            RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder
        = ViewHolder(MultiImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binnding = (holder as ViewHolder).binnding
            // Glide 이미지 전문으로 처리 해주는 API
            // bitmapFactory options 설정, 이상한 calc 함수통해서 리사이즈.
            // context -> 현재 액티비티 -> this@MainActivity.
            // load , 사진을 불러오기. items => list , 각사진의 Uri 주소가 담아져 있음.
            // override -> 직접 원하는 사이즈 적어주면,
            // into , 해당 뷰에 , 사진을 넣는 작업.
            Glide.with(context).load(items[position])
                .override(500,500)
                .into(binnding.image)
        }

        override fun getItemCount(): Int {
            return items.size
        }


    }
}