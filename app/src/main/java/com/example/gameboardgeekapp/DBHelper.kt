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
        getUserData(username)
    }
    fun getUser(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM USERS", null)
    }

    fun getUserData(username: String){
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

}