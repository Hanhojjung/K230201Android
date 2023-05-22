package com.example.ch16_provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ch16_provider.databinding.FragmentFourBinding
import com.example.ch16_provider.databinding.FragmentThreeBinding


class FourFragment : Fragment() {
    private var _binding: FragmentFourBinding? = null
    private val binding get() = _binding!!
    lateinit var filePath: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourBinding.inflate(inflater, container, false)
        val view = binding.root


        return view

    }

}