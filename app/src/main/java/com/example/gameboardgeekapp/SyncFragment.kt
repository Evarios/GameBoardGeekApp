package com.example.gameboardgeekapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_games.*
import kotlinx.android.synthetic.main.fragment_main_screen.*
import kotlinx.android.synthetic.main.fragment_sync.*
import kotlinx.android.synthetic.main.fragment_sync.idMainMenu

class SyncFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sync, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = DBHelper(requireActivity().applicationContext, null)
        val cursor = db.getUser()
        cursor!!.moveToFirst()
        var username = cursor.getString(cursor.getColumnIndex("USERNAME"))
        idSyncDate.append("Ostatnia synchronizacja: " + cursor.getString(cursor.getColumnIndex("LAST_SYNCED")))
        idBtnSync.setOnClickListener {
            val activity = requireActivity()
            val builder = AlertDialog.Builder(activity)
            builder.setMessage("Czy na pewno chcesz zsynchronizować aplikację?")
                .setCancelable(false)
                .setPositiveButton("Tak") { dialog, id ->
                    val db = DBHelper(requireActivity().applicationContext, null)
                    db.sync(username)
                    val text = "Trwa synchronizacja"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(requireActivity().applicationContext, text, duration)
                    toast.show()
                    Thread.sleep(2_000)
                    (activity as MainActivity).loadMainScreen(savedInstanceState)
                }
                .setNegativeButton("Nie") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
        idMainMenu.setOnClickListener {
            val db = DBHelper(requireActivity().applicationContext, null)
            (activity as MainActivity).loadMainScreen(savedInstanceState)
        }

    }
}