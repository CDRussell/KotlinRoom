package com.cdrussell.myapplication.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Bin(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var name: String = ""
)