package com.example.ch16_provider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ch16_provider.databinding.FragmentThreeBinding

class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding? = null
    private val binding get() = _binding!!

    private lateinit var myDB: DatabaseHelper
    private lateinit var editTextName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPass: EditText
    private lateinit var editTextID: EditText
    private lateinit var buttonInsert: Button
    private lateinit var buttonView: Button
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        val view = binding.root

        myDB = DatabaseHelper(requireContext())
        editTextName = binding.editTextName
        editEmail = binding.editEmail
        editPass = binding.editPass
        editTextID = binding.editTextID
        buttonInsert = binding.buttonInsert
        buttonView = binding.buttonView
        buttonUpdate = binding.buttonUpdate
        buttonDelete = binding.buttonDelete

        addButtonListeners()
        return view
    }

    private fun addButtonListeners() {
        buttonInsert.setOnClickListener {
            val isInserted = myDB.insertData(
                editTextName.text.toString(),
                editEmail.text.toString(),
                editPass.text.toString()
            )
            if (isInserted == true)
                Toast.makeText(requireContext(), "데이터 추가 성공", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(requireContext(), "데이터 추가 실패", Toast.LENGTH_LONG).show()
        }

        buttonView.setOnClickListener {
            val res = myDB.allData
            if (res.count == 0) {
                showMessage("실패", "데이터를 찾을 수 없습니다.")
                return@setOnClickListener
            }

            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("ID: ${res.getString(0)}\n")
                buffer.append("이름: ${res.getString(1)}\n")
                buffer.append("전화번호: ${res.getString(2)}\n")
                buffer.append("주소: ${res.getString(3)}\n\n")
            }
            showMessage("데이터", buffer.toString())
        }

        buttonUpdate.setOnClickListener {
            val isUpdated = myDB.updateData(
                editTextID.text.toString(),
                editTextName.text.toString(),
                editEmail.text.toString(),
                editPass.text.toString()
            )
            if (isUpdated == true)
                Toast.makeText(requireContext(), "데이터 수정 성공", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(requireContext(), "데이터 수정 실패", Toast.LENGTH_LONG).show()
        }

        buttonDelete.setOnClickListener {
            val deleteRows = myDB.deleteData(editTextID.text.toString())
            if (deleteRows > 0)
                Toast.makeText(requireContext(), "데이터 삭제 성공", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(requireContext(), "데이터 삭제 실패", Toast.LENGTH_LONG).show()
        }
    }

    private fun showMessage(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
    }
}
