package com.example.maheshbhattarai.sqlite_database_demo.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Location")
public class Location(time: Date, long: Double, lat: Double) {

    @PrimaryKey(autoGenerate = false)
    var date: Date? =null

    @ColumnInfo(name = "latitude")
    var latitude: Double? = null

    @ColumnInfo(name = "longitude")
    var longitude: Double? = null
}
