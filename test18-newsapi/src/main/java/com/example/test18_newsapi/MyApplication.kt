package com.example.test18_pdtest_pdtesti_newsapi

import android.app.Application
import com.example.test18_pdtest_pdtesti_newsapi.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object {
        val QUERY = "travel"
      /*  val QUERY2 = "apple"*/
        val from ="2023-05-22"
        val sortBy="popularity"
        val API_KEY = "ad3aea573a594fc8840b0f964ff92f52"
//        val API_KEY = "079dac74a5f94ebdb990ecf61c8854b7"
        val BASE_URL = "https://newsapi.org"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"


        //add....................................
        var networkService: NetworkService
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}