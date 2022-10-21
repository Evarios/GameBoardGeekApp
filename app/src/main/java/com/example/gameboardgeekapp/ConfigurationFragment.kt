package com.example.gameboardgeekapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_configuration.*

class ConfigurationFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configuration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idBtnAddUsername.setOnClickListener {
            val db = DBHelper(requireActivity().applicationContext, null)
            val userName = idEnterUsername.text.toString()
            db.addUser(userName)
            idEnterUsername.text.clear()
            (activity as MainActivity).loadMainScreen(savedInstanceState)
        }
    }
}