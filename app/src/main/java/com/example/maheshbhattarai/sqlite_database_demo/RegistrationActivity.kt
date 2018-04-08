package com.example.maheshbhattarai.sqlite_database_demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.maheshbhattarai.sqlite_database_demo.database.AppDatabase
import com.example.maheshbhattarai.sqlite_database_demo.database.Registration


class RegistrationActivity : AppCompatActivity() {

    private var mDb: AppDatabase? = null
    private var radioRole: RadioGroup? = null
    private var radioRoleButton: RadioButton? = null
    lateinit var role: String
    lateinit var login : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val address = findViewById<EditText>(R.id.address)
        val phone = findViewById<EditText>(R.id.phoneNumber)
        val password = findViewById<EditText>(R.id.password)

        login  = findViewById(R.id.login)
        login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        })

        radioRole = findViewById(R.id.radioRole)

        val btnShow = findViewById<Button>(R.id.btn_submit)
        btnShow?.setOnClickListener {
            val selectedId = radioRole?.getCheckedRadioButtonId()

            // find the radiobutton by returned id
            radioRoleButton = findViewById<View>(selectedId!!) as RadioButton

           // Toast.makeText(this, "" + radioRoleButton!!.getText(), Toast.LENGTH_SHORT).show();
            role = radioRoleButton!!.getText().toString()

            if (name.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show()
            } else if (email.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            } else if (address.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show()
            } else if (phone.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
            } else if (password.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            } else if (role.isEmpty()) {
                Toast.makeText(this, "Select Role", Toast.LENGTH_SHORT).show()
            } else {
                val employee = Registration()
                employee.firstName = name.text.toString()
                employee.email = email.text.toString()
                employee.address = address.text.toString()
                employee.phone_number = phone.text.toString()
                employee.password = password.text.toString()
                employee.role = role
                mDb?.employDao()?.insertEmploy(employee)
                Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
                name.setText("")
                address.setText("")
                email.setText("")
                phone.setText("")
                password.setText("")
                name.requestFocus()

                val userList = mDb?.employDao()?.findAllEmploySync()
                Log.e("Insert ", "Rows Count: " + userList?.get(0)!!.firstName)

                for (employee in userList) {
                    Log.e("name", employee.firstName)
                }
                /*val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()*/
            }
        }

    }
}
