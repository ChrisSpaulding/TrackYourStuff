package com.example.trackYourStuff.sqlite_database_demo.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "job_list")
public class Job_List {

    @PrimaryKey(autoGenerate = true)
    var jobId: Long = 0

    @ColumnInfo(name = "jobt_name")
    var jobt_name: String? = null

    @ColumnInfo(name = "job_description")
    var job_description: String? = null

    @ColumnInfo(name = "start_time")
    var start_time: String? = null

    @ColumnInfo(name = "end_time")
    var end_time: String? = null

    @ColumnInfo(name = "estimated_time")
    var estimated_time: String? = null

    @ColumnInfo(name = "amount")
    var amount: String? = null

    @ColumnInfo(name = "apply")
    var apply: String? = null

}