package com.example.meine_zitaten.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "zitate_table")
data class Zitate(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val author: String,
    val age: Int,

    ): Parcelable


