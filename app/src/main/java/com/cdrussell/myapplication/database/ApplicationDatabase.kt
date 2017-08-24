package com.cdrussell.myapplication.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.cdrussell.myapplication.domain.Bin
import com.cdrussell.myapplication.domain.BinDao


@Database(entities = arrayOf(Bin::class), version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
}