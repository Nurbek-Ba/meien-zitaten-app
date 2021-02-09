package com.example.meine_zitaten.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.meine_zitaten.model.Zitate

@Dao
interface ZitateDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addZitate(zitate: Zitate)
    @Update
    suspend fun updateZitate(zitate: Zitate)

    @Delete
    suspend fun deleteZitate(zitate: Zitate)

    @Query("DELETE FROM zitate_table")
    suspend fun deleteAllZitaten()

    @Query("SELECT * FROM zitate_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Zitate>>
}