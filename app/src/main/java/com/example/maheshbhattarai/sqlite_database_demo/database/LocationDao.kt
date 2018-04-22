package com.example.maheshbhattarai.sqlite_database_demo.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import java.util.*

@Dao
interface LocationDao {

    @Insert(onConflict = REPLACE)
    fun insertLocation(location: Location)

    @Update(onConflict = REPLACE)
    fun updateEmploy(employee: Registration)

    @Query("DELETE FROM Location")
    fun deleteAll()

    @Query("SELECT * FROM registration")
    fun findAllEmploySync(): List<Registration>

    @Query("SELECT * FROM Location where Date = :mDate ")
    fun findLocation(mDate : Date): List<Location>


}