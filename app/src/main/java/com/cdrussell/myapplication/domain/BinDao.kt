package com.cdrussell.myapplication.domain

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


@Dao
interface BinDao {
    @Query("SELECT * FROM bin")
    fun getAllBins(): Flowable<List<Bin>>

    @Insert
    fun insert(bin: Bin)

    @Delete
    fun deleteBin(bin: Bin) : Int
}