package com.example.trackYourStuff.sqlite_database_demo.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import java.util.*

@Dao
interface LocationDao {

    @Insert(onConflict = REPLACE)
    fun insertLocation(location: Location)

    @Query("DELETE FROM Location")
    fun deleteAll()


    @Query("SELECT * FROM Location where Date = :mDate ")
    fun findLocation(mDate : Date): List<Location>


}