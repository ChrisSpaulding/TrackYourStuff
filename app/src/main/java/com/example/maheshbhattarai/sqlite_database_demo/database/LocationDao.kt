package com.example.maheshbhattarai.sqlite_database_demo.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import java.util.*

@Dao
interface LocationDao {

    @Insert(onConflict = REPLACE)
    abstract fun insertLocation(location: Location)

    @Update(onConflict = REPLACE)
    fun updateEmploy(employee: Registration)

    @Query("DELETE FROM Location")
    fun deleteAll()

    @Query("SELECT * FROM registration")
    fun findAllEmploySync(): List<Registration>

    @Query("SELECT * FROM Location where date = Date")
    fun findLocation(Date : Date)

}