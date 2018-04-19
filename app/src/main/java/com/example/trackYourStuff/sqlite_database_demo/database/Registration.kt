package com.example.trackYourStuff.sqlite_database_demo.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "registration")
public class Registration (

    @PrimaryKey(autoGenerate = true)
    var employId: Long = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "address")
    var address: String? = "",

    @ColumnInfo(name = "phone_number")
    var phone_number: String? = "",

    @ColumnInfo(name = "password")
    var password: String? = "",

    @ColumnInfo(name = "role")
    var role: String? = ""

)