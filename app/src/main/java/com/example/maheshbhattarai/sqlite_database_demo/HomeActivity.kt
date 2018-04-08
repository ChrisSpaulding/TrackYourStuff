package com.example.maheshbhattarai.sqlite_database_demo

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.content.SharedPreferences
import android.R.id.edit
import android.annotation.SuppressLint

class HomeActivity : AppCompatActivity() {

    lateinit var btn_company: Button
    lateinit var btn_jobSeeker: Button
    var sharedpreferences: SharedPreferences? = null

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedpreferences = getSharedPreferences("role", Context.MODE_PRIVATE);
        val editor = sharedpreferences?.edit()

        btn_company = findViewById(R.id.btn_company)
        btn_jobSeeker = findViewById(R.id.btn_jobSeeker)

        btn_company.setOnClickListener(View.OnClickListener {
            editor?.putString("login_type", "Employeer");
            editor?.commit();
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })

        btn_jobSeeker.setOnClickListener(View.OnClickListener {
            editor?.putString("login_type", "Job_Seeker");
            editor?.commit();
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        })

    }
}
