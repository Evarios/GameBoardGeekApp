package com.example.gameboardgeekapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class DBHelper(private val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val queryCreateUsersTable = "CREATE TABLE USERS(ID INTEGER PRIMARY KEY, USERNAME TEXT, GAME_NUM INTEGER, EXPANSION_NUM INTEGER, LAST_SYNCED TEXT)"
        db.execSQL(queryCreateUsersTable)
        val queryCreateGamesTable = "CREATE TABLE GAMES(ID INTEGER PRIMARY KEY, TITLE TEXT, RELEASE_YEAR INTEGER, RANKING INTEGER, IS_EXTENSION INTEGER, IMG_URL TEXT, LAST_SYNCED TEXT)"
        db.execSQL(queryCreateGamesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS USERS")
        db.execSQL("DROP TABLE IF EXISTS GAMES")
        onCreate(db)
    }

    fun addUser(username: String){
        val values = ContentValues()
        values.put("USERNAME", username)
        values.put("GAME_NUM", 0)
        values.put("EXPANSION_NUM", 0)
        values.put("LAST_SYNCED", "2022-01-01")
        val db = this.writableDatabase
        db.insert("USERS",null, values)
        db.close()
        //sync(username)
        parseXMLtoDB()
    }
    fun getUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM USERS", null)
    }

    fun sync(username: String){
        val queue = Volley.newRequestQueue(context)
        var url = "https://www.boardgamegeek.com/xmlapi2/collection?username=$username&stats=1"
        var responseString = ""
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                responseString = response.toString()
                response.toString()
            },
            { error ->
                Log.e("MainActivity", "loadDogImage error: ${error.localizedMessage}")
            })
        queue.add(stringRequest)
    }

    fun parseXMLtoDB(){
//        val inputStream: InputStream = context.assets.open("collection.xml")
//        val size: Int = inputStream.available()
//        val buffer = ByteArray(size)
//        inputStream.read(buffer)
//        var string = String(buffer)

        val input: InputStream = context.assets.open("collection.xml")
        val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
        val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
        //var xmlString = input.toString()
        val doc: Document = dBuilder.parse(input)

        val element: Element = doc.documentElement
        element.normalize()

        val nList: NodeList = doc.getElementsByTagName("item")

        for (i in 0 until nList.length) {
            val node: Node = nList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val element2 = node as Element
                Log.d("TestValue", "value: " + element2.getElementsByTagName("name").item(0).textContent)
//                tv1.setText(tv1.getText() + "\nName : " + getValue("name", element2) + "\n")
//                tv1.setText(tv1.getText() + "Surname : " + getValue("surname", element2) + "\n")
//                tv1.setText(tv1.getText() + "-----------------------")
            }
        }
    }

}