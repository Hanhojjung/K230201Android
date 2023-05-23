package com.example.test18

import com.example.test18.retrofit.INetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication {
    var networkService: INetworkService


    val retrofit: Retrofit
    get() = Retrofit.Builder()
        .baseUrl("https://reqres.in/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init {
        networkService = retrofit.create(INetworkService::class.java)
    }
}