package com.example.gameboardgeekapp

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_games.*
import java.io.InputStream
import java.net.URL


class GamesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val threadPolicy :StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(threadPolicy)
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = DBHelper(requireActivity().applicationContext, null)
        val names = db.getGameNames()
        val years = db.getGameYears()
        val BGGIDs = db.getBGGIDs()
        val ranks = db.getGameRanks()
        val imgs = db.getGameIMGs()
        val myListAdapter = GameDataHandler(requireActivity(), gameNames = names, gameYears = years, bggIDs = BGGIDs, gameRanks = ranks, gameImageUrls = imgs)
        gameListView.adapter = myListAdapter
    }

}