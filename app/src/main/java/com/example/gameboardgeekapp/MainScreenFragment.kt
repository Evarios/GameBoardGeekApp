package com.example.gameboardgeekapp

import android.app.AlertDialog
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
import kotlinx.android.synthetic.main.fragment_sync.*

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
        userName.append("Username: " + cursor.getString(cursor.getColumnIndex("USERNAME")))
        userGameNum.append("Liczba gier: " + cursor.getString(cursor.getColumnIndex("GAME_NUM")))
        userLastSync.append("Ostatnia synchronizacja: " + cursor.getString(cursor.getColumnIndex("LAST_SYNCED")))
        idBtnDeleteData.setOnClickListener {
            val activity = requireActivity()
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Czy na pewno chcesz usunąć dane??")
                .setCancelable(false)
                .setPositiveButton("Tak") { dialog, id ->
                    activity.applicationContext.deleteDatabase(DB_NAME);
                    activity.moveTaskToBack(true);
                    activity.finish();
                }
                .setNegativeButton("Nie") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()

        }
        idGameData.setOnClickListener {
            val db = DBHelper(requireActivity().applicationContext, null)
            (activity as MainActivity).loadGameList(savedInstanceState)
        }
        idSync.setOnClickListener {
            val db = DBHelper(requireActivity().applicationContext, null)
            (activity as MainActivity).loadSyncScreen(savedInstanceState)
        }
    }
    //button syn
    //button game list vierw

}