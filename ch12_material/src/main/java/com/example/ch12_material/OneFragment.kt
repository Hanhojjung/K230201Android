package com.example.ch12_material

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ch12_material.databinding.FragmentOneBinding


class OneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOneBinding.inflate(inflater,container,false)

        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }



        return super.onCreateView(inflater, container, savedInstanceState)
    }
}