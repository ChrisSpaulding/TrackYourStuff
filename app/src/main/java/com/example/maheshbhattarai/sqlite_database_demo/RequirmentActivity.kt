package com.example.maheshbhattarai.sqlite_database_demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.TimePickerDialog
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.maheshbhattarai.sqlite_database_demo.database.AppDatabase
import com.example.maheshbhattarai.sqlite_database_demo.database.Job_List
import java.util.*


class RequirmentActivity : AppCompatActivity() {

    private var mDb: AppDatabase? = null

    var number1 = 0
    var number2 = 0
    var result: Double = 0.0
    lateinit var job_name: EditText
    lateinit var job_description: EditText
    lateinit var start_time: EditText
    lateinit var end_time: EditText
    lateinit var estimated_time: EditText
    lateinit var amount: EditText
    lateinit var btn_submit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirment)

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        job_name = findViewById<EditText>(R.id.job_title)
        job_description = findViewById<EditText>(R.id.job_description)
        start_time = findViewById<EditText>(R.id.start_time)
        end_time = findViewById<EditText>(R.id.end_time)
        estimated_time = findViewById<EditText>(R.id.estimated_time)
        amount = findViewById(R.id.amount)

        btn_submit = findViewById(R.id.btn_submit)
        btn_submit.setOnClickListener(View.OnClickListener {
            submitJob()
        })

        start_time.setOnClickListener(View.OnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                start_time.setText(selectedHour.toString() + ":" + selectedMinute)
                number1 = selectedHour
                getDifference()
            }, hour, minute, false)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })

        end_time.setOnClickListener(View.OnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                end_time.setText(selectedHour.toString() + ":" + selectedMinute)
                number1 = selectedHour
                getDifference()
            }, hour, minute, false)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })

    }

    private fun submitJob() {
        if (job_name.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Job Title", Toast.LENGTH_SHORT).show()
        } else if (job_description.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Job Description", Toast.LENGTH_SHORT).show()
        } else if (start_time.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Start Time", Toast.LENGTH_SHORT).show()
        } else if (end_time.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter End Time", Toast.LENGTH_SHORT).show()
        } else if (estimated_time.text.toString().isEmpty()) {
            Toast.makeText(this, "Enter Estimated Title", Toast.LENGTH_SHORT).show()
        } else if (amount.text.toString().isEmpty()) {
            Toast.makeText(this,"Enter Amount",Toast.LENGTH_SHORT).show()
        }else{
            val job_list = Job_List()
            job_list.jobt_name = job_name.text.toString()
            job_list.job_description = job_description.text.toString()
            job_list.start_time = start_time.text.toString()
            job_list.end_time = end_time.text.toString()
            job_list.estimated_time = estimated_time.text.toString()
            job_list.amount = amount.text.toString()
            job_list.apply = "0"

            mDb?.employDao()?.insertJobs(job_list)
            Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show()
            job_name.setText("")
            job_description.setText("")
            start_time.setText("")
            end_time.setText("")
            estimated_time.setText("")
            amount.setText("")
            job_name.requestFocus()
        }
    }

    private fun getDifference() {
        if (number1 != null && number2 != null) {
            result = (number1 - number2).toDouble()
            println("number1 - number2 = $result")
            val hours = Math.abs(result.toInt()).toString()
            Log.e("result", Math.abs(result.toInt()).toString())
            estimated_time.setText(hours)
        }
    }

}
