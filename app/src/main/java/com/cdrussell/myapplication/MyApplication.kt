package com.cdrussell.myapplication

import android.app.Application
import android.arch.persistence.room.Room
import com.cdrussell.myapplication.database.ApplicationDatabase


class MyApplication : Application() {

    companion object {
        var database: ApplicationDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.database = Room.databaseBuilder(this, ApplicationDatabase::class.java, "Application.db").build()
    }

}