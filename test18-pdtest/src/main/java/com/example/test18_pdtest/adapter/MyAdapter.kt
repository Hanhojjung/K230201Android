package com.example.test18_pdtest.adapter
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test18_pdtest.Model.UserModel
import com.example.test18_pdtest.MyApplication
import com.example.test18_pdtest.databinding.ItemRetrofitBinding

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<UserModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        // datas -> List<UserModel> 한 요소가, 한 멤버의 객체입니다.
        val user = datas?.get(position)
        //받아온 데이터를 사용 다 안함
        //제목, 썸네일 이미지 사용할 예정
        binding.id.text=user?.title

//        binding.emailView.text =user?.email

        user?.mainImgThumb?.let {
            val avatarImageCall = (context.applicationContext as MyApplication).networkService.getAvatarImage(it)
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
                            binding.avatarView.setImageBitmap(bitmap)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }



    }

}
