package com.example.trackYourStuff.sqlite_database_demo

import java.util.Date

data class GeoLocation(
        val date: Date,
        val longitude: Long,
        val latitude: Long
)