package com.example.gameboardgeekapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class CustomCountryList(
    private val context: Activity,
    private val gameIDs: ArrayList<Int>,
    private val gameNames: Array<String>,
    private val gameYears: Array<Int>,
    private val bggIDs: Array<Int>,
    private val gameRanks: Array<Int>,
    private val gameImageUrls: Array<String>
) :
    ArrayAdapter<String?>(context, R.layout.game_row_item, gameIDs) {
    fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var row: View? = convertView
        val inflater = context.layoutInflater
        if (convertView == null) row = inflater.inflate(R.layout.row_item, null, true)
        val textViewCountry = row.findViewById(R.id.textViewCountry) as TextView
        val textViewCapital = row.findViewById(R.id.textViewCapital) as TextView
        val imageFlag: ImageView = row.findViewById(R.id.imageViewFlag) as ImageView
        textViewCountry.text = countryNames[position]
        textViewCapital.text = capitalNames[position]
        imageFlag.setImageResource(imageid[position])
        return row
    }
}
