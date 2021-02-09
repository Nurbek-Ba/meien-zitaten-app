package com.example.meine_zitaten.repository

import androidx.lifecycle.LiveData
import com.example.meine_zitaten.data.ZitateDao
import com.example.meine_zitaten.model.Zitate

class ZitateRepository(private val zitateDao: ZitateDao){

    val readAllData: LiveData<List<Zitate>> = zitateDao.readAllData()


    suspend fun addZitate(zitate: Zitate){
        zitateDao.addZitate(zitate)
    }

    suspend fun updateZitate(zitate: Zitate){
        zitateDao.updateZitate(zitate)
    }

    suspend fun deleteZitate(zitate: Zitate){
        zitateDao.deleteZitate(zitate)
    }

    suspend fun deleteAllZitaten(){
        zitateDao.deleteAllZitaten()
    }
}