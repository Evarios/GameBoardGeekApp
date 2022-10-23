package com.example.gameboardgeekapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import java.io.File

const val DB_NAME = "bgg_db"
const val DB_VERSION = 1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
//            if(doesDatabaseExist(applicationContext, DB_NAME)){
//                loadMainScreen(savedInstanceState)
//            }
//            else{
//                loadConfiguration(savedInstanceState)
//            }
            loadGameList(savedInstanceState)
        }
    }

    private fun doesDatabaseExist(context: Context, dbName: String): Boolean {
        val dbFile: File = context.getDatabasePath(dbName)
        return dbFile.exists()
    }

    private fun loadConfiguration(savedInstanceState: Bundle?){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_placeholder, ConfigurationFragment())
        }
    }

    fun loadMainScreen(savedInstanceState: Bundle?){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_placeholder, MainScreenFragment())
        }
    }

    fun loadGameList(savedInstanceState: Bundle?){
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_placeholder, GamesFragment())
        }
    }

}