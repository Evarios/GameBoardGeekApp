package com.example.gameboardgeekapp

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.InputStream
import java.net.URL
import java.util.SimpleTimeZone


class GameDataHandler(
    private val context: Activity,
   // private val gameIDs: ArrayList<String>,
    private val gameNames: ArrayList<String>,
    private val gameYears: ArrayList<String>,
    private val bggIDs: ArrayList<String>,
    private val gameRanks: ArrayList<String>,
    private val gameImageUrls: ArrayList<String>
) : ArrayAdapter<String>(context, R.layout.game_row_item, gameNames) {
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.game_row_item, null, true)
        val gameNameText = rowView.findViewById(R.id.textGameName) as TextView
        val imageView = rowView.findViewById(R.id.imageGameThumbnail) as ImageView
        val subtitleText = rowView.findViewById(R.id.textGameData) as TextView
        gameNameText.text = gameNames[position] + " (" + gameYears[position].toString() +")"
        subtitleText.text = "BGG ID: " + bggIDs[position].toString() + " rank: " + gameRanks[position].toString()
        val img = LoadImageFromWebOperations(gameImageUrls[position])
        imageView.setImageDrawable(img)
//        var row: View? = convertView
//        val inflater = context.layoutInflater
//        if (convertView == null) row = inflater.inflate(R.layout.row_item, null, true)
//        val textViewCountry = row.findViewById(R.id.textViewCountry) as TextView
//        val textViewCapital = row.findViewById(R.id.textViewCapital) as TextView
//        val imageFlag: ImageView = row.findViewById(R.id.imageViewFlag) as ImageView
//        textViewCountry.text = countryNames[position]
//        textViewCapital.text = capitalNames[position]
//        imageFlag.setImageResource(imageid[position])
        return rowView
    }

    fun LoadImageFromWebOperations(url: String?): Drawable? {
        val imgStream: InputStream = URL(url).getContent() as InputStream
        return Drawable.createFromStream(imgStream, "src name")
    }
}
