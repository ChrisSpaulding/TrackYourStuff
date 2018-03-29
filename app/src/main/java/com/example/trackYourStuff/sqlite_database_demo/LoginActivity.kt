package com.example.trackYourStuff.sqlite_database_demo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.trackYourStuff.sqlite_database_demo.database.AppDatabase


class LoginActivity : AppCompatActivity() {


    private var mDb: AppDatabase? = null
    var sharedpreferences: SharedPreferences? = null
    var sharedpreferencesID: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedpreferences = getSharedPreferences("role", Context.MODE_PRIVATE);
        //val editor = sharedpreferences?.edit()

        sharedpreferencesID = getSharedPreferences("id", Context.MODE_PRIVATE);
        val editorID = sharedpreferences?.edit()



        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        val currentDBPath = getDatabasePath("simple.db").absolutePath
        Log.e("database_path", currentDBPath)

        val emailId = findViewById<EditText>(R.id.emailId)
        val password = findViewById<EditText>(R.id.password)


        val login_type = sharedpreferences?.getString("login_type", "")
        Log.e("login_type", login_type)


        val btn_login = findViewById<Button>(R.id.btn_login)

        btn_login.setOnClickListener(View.OnClickListener {
            if (login_type?.contains("Employeer")!!) {
                if (emailId.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                } else if (password.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                } else {

                    //

                    val employeeList = mDb?.employDao()?.findAllEmploySync()
                    Log.e("size", employeeList?.size.toString())

                    if (employeeList != null) {

                        for (employee in employeeList) {
                            Log.e("email", employee.firstName)
                            if (emailId.text.toString().equals(employee.email) && password.text.toString().equals(employee.password)) {
                                /* Log.e("email", employee.email)
                                 Log.e("email", employee.password)*/
                                Log.e("seekerID",employee.employId.toString())
                                val intent = Intent(this, RequirmentActivity::class.java)
                                startActivity(intent)

                            } else {
                                //Toast.makeText(this, "Email Id and Password not matched", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Email Id and Password not matched", Toast.LENGTH_SHORT).show()
                    }

                }

            } else {

                if (emailId.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                } else if (password.text.toString().trim().isEmpty()) {
                    Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
                } else {

                    val employeeList = mDb?.employDao()?.findAllEmploySync()
                    Log.e("size", employeeList?.size.toString())

                    if (employeeList != null) {

                        for (employee in employeeList) {
                            Log.e("email", employee.firstName)
                            if (emailId.text.toString().equals(employee.email) && password.text.toString().equals(employee.password)) {
                                /* Log.e("email", employee.email)
                                 Log.e("email", employee.password)*/
                                Log.e("seekerID",employee.employId.toString())
                                editorID?.putString("seekerID",employee.employId.toString())
                                editorID?.commit()
                                val intent = Intent(this, Job_Listing::class.java)
                                startActivity(intent)

                            } else {
                                //Toast.makeText(this, "Email Id and Password not matched", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Email Id and Password not matched", Toast.LENGTH_SHORT).show()
                    }

                }


            }

        })

        val new_account = findViewById<TextView>(R.id.new_account)

        new_account.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        })

    }
}
