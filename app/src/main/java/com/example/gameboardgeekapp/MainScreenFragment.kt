package com.example.gameboardgeekapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.android.synthetic.main.fragment_configuration.*
import kotlinx.android.synthetic.main.fragment_main_screen.*

class MainScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = DBHelper(requireActivity().applicationContext, null)
        val cursor = db.getUser()
        cursor!!.moveToFirst()
        userName.append(cursor.getString(cursor.getColumnIndex("USERNAME")))
        idBtnDeleteData.setOnClickListener {
            val activity = requireActivity()
            activity.applicationContext.deleteDatabase(DB_NAME);
            activity.moveTaskToBack(true);
            activity.finish();
        }
    }

}