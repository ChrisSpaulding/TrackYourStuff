package com.example.trackYourStuff.sqlite_database_demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

// Jesus can comment now.
class MainActivity : AppCompatActivity() {

    lateinit var btn_androidmap: Button
    lateinit var btn_androidsqlite: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_androidmap = findViewById(R.id.btn_androidmap)
        btn_androidsqlite = findViewById(R.id.btn_androidsqlite)

        btn_androidmap.setOnClickListener(View.OnClickListener {


            val intent = Intent(this, SearchPlacesActivity::class.java)
            startActivity(intent)
        })

        btn_androidsqlite.setOnClickListener(View.OnClickListener {

             val intent = Intent(this,HomeActivity::class.java)
             startActivity(intent)
        })

        btn_location.setOnClickListener{
            val intent = Intent ( this, LocationLogger::class.java)
            startActivity(intent)
        }


    }
}
