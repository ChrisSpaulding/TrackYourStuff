package com.example.maheshbhattarai.sqlite_database_demo.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.maheshbhattarai.sqlite_database_demo.CustomDate

@Entity(tableName = "Location")
public class Location {

    @PrimaryKey(autoGenerate = false)
    var date: CustomDate? =null

    @ColumnInfo(name = "latitude")
    var latitude: Int? = null

    @ColumnInfo(name = "longitude")
    var longitude: Int? = null
}
