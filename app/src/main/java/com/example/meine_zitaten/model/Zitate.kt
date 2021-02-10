package com.example.meine_zitaten.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "zitate_table")
data class Zitate(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var text: String="",
    var author: String="",
    var count: Int,
    var isDone: Boolean = false,

): Parcelable


