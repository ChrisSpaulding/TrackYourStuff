package com.example.trackYourStuff.sqlite_database_demo

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.trackYourStuff.sqlite_database_demo.database.AppDatabase
import com.example.trackYourStuff.sqlite_database_demo.database.Registration
import com.example.trackYourStuff.sqlite_database_demo.database.RegistrationDao
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTesting {

    private var registrationDao : RegistrationDao? = null

    @Before
    fun createDB(){
        AppDatabase.TEST_MODE = true
        registrationDao = AppDatabase.getInstance(InstrumentationRegistry.getTargetContext())
    }

    @After
    fun closeDb() {

    }

    @Test
    fun should_Insert_Registration(){
        val registration = Registration(1,"David","Aguiardavidm@gmail.com","1234 Sesame Street","1234567891","password","boss")
        registrationDao?.insertEmploy(registration)
    }


}
