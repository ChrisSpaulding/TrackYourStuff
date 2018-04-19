package com.example.trackYourStuff.sqlite_database_demo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.test.MoreAsserts.assertEquals
import com.example.trackYourStuff.sqlite_database_demo.database.AppDatabase
import com.example.trackYourStuff.sqlite_database_demo.database.Registration
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class DatabaseTesting {
    protected lateinit var appDatabase: AppDatabase

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java)
                .build()
    }

    @Test
    fun insertEmployTest(){
        val employee = Registration(employId = 0, firstName = "David",email = "AguiarDavidM@gmail.com",address = "123 Hello St",phone_number = "303-123-4567",password = "pass",role= "the boss")
        val employeeID = appDatabase.employDao().insertEmploy(employee)
        val employeeDB = appDatabase.employDao().findEmployByID(0)
        assert(employeeDB.toString() == employee.toString())

    }
    /*@Test
    fun insertUserTest() {
        val user = User(userName = "Test User")
        val userId = appDatabase.userDao().insertUser(user)
        val userFromDb = appDatabase.userDao().user
        assertEquals(userId, 1)
        assertEquals(userFromDb?.userName, user.userName)
    }*/

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Throws(InterruptedException::class)
    fun <T> LiveData<T>.getValueBlocking(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)
        val innerObserver = Observer<T> {
            value = it
            latch.countDown()
        }
        observeForever(innerObserver)
        latch.await(2, TimeUnit.SECONDS)
        return value
    }


}
