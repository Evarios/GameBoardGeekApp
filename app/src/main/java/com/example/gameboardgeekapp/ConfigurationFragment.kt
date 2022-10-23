package com.example.gameboardgeekapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            val text = "Trwa synchronizacja"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(requireActivity().applicationContext, text, duration)
            toast.show()
            Thread.sleep(2_000)
            (activity as MainActivity).loadMainScreen(savedInstanceState)
        }
    }
}