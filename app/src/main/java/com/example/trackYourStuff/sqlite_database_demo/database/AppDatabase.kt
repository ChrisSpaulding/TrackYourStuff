package com.example.trackYourStuff.sqlite_database_demo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(Registration::class,Job_List::class), version = 2)
abstract class AppDatabase : RoomDatabase() {


    abstract fun employDao(): RegistrationDao

    companion object {

        var TEST_MODE = false

        private var INSTANCE: AppDatabase? = null
        private var dbInstance: RegistrationDao? = null

        fun getInstance(context: Context): RegistrationDao{
            if(dbInstance==null){
                if(TEST_MODE){
                    INSTANCE = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).allowMainThreadQueries().build()
                    dbInstance = INSTANCE?.employDao()
                } else {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "database_name").build()
                    dbInstance = INSTANCE?.employDao()
                }
            }
            return dbInstance!!
        }

        fun getInMemoryDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase::class.java,"simple.db")
                        // To simplify the codelab, allow queries on the main thread.
                        // Don't do this on a real app! See PersistenceBasicSample for an example.
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