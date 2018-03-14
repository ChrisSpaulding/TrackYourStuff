package com.example.maheshbhattarai.sqlite_database_demo.adapter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.maheshbhattarai.sqlite_database_demo.R
import com.example.maheshbhattarai.sqlite_database_demo.database.Job_List

/**
 * Created by Mahesh Bhattarai on 2/23/2018.
 */
class CustomAdapter(val userList: List<Job_List>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    lateinit var context : Context
    //var sharedpreferencesID: SharedPreferences? = null
    lateinit var login_type : String
    //this method is returning the view for each item in the list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        /*sharedpreferencesID = context.getSharedPreferences("id", Context.MODE_PRIVATE);
        login_type = sharedpreferencesID?.getString("id", "").toString()
        Log.e("login_type", login_type)*/
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_row_data, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Job_List) {
            val job_title = itemView.findViewById<TextView>(R.id.job_title)
            val time = itemView.findViewById<TextView>(R.id.job_start_end_time)
            val estimatedTime = itemView.findViewById<TextView>(R.id.estimated_time)
            val amount = itemView.findViewById<TextView>(R.id.amount)
            val jobDescription = itemView.findViewById<TextView>(R.id.job_description)

            job_title.text = user.jobt_name
            time.text = user.start_time +" - "+ user.end_time
            estimatedTime.text = user.estimated_time
            amount.text = user.amount
            jobDescription.text = user.job_description

            itemView.setOnClickListener {
                val pos = adapterPosition
               Toast.makeText(itemView.context, user.jobId.toString(), Toast.LENGTH_SHORT).show()
            }

        }
    }
}