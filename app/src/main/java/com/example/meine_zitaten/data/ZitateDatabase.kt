package com.example.meine_zitaten.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meine_zitaten.model.Zitate

@Database(entities = [Zitate::class], version = 3, exportSchema = false)
abstract class ZitateDatabase: RoomDatabase() {

    abstract fun zitateDao(): ZitateDao

    companion object{
        @Volatile
        private var INSTANCE: ZitateDatabase? = null

        fun getDatabase(context: Context): ZitateDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZitateDatabase::class.java,
                    "zitate_database"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}