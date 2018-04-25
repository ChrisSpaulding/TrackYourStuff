package com.example.trackYourStuff.sqlite_database_demo.database

import android.arch.persistence.room.*
import android.content.Context

@Database(entities = arrayOf( Job_List::class, Location::class), version = 1)
@TypeConverters(DateConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun locationDao() : LocationDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInMemoryDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase::class.java,"simple.db")
                        // To simplify the codelab, allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
                        //TODO remove main thread queries
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}