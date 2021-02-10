package com.example.meine_zitaten.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.meine_zitaten.data.ZitateDatabase
import com.example.meine_zitaten.data.ZitateDatabase.Companion.getDatabase
import com.example.meine_zitaten.model.Zitate
import com.example.meine_zitaten.repository.ZitateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ZitatenViewModel(application: Application) : AndroidViewModel(application){
    val readAllData: LiveData<List<Zitate>>

    val zitaten = mutableListOf<Zitate>()
//    val isDone = mutableListOf<Zitate>()

    val isDoneZitate = mutableListOf<Zitate>()

    private val repository: ZitateRepository
    init {
        val zitateDao = ZitateDatabase.getDatabase(application).zitateDao()
        repository = ZitateRepository(zitateDao)
        readAllData = repository.readAllData
    }

    fun addZitate(zitate: Zitate){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.addZitate(zitate)
            }
        }
    }

    fun updateZitate(zitate: Zitate){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateZitate(zitate)
        }
    }
    fun deleteZitate(zitate: Zitate){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteZitate(zitate)
        }
    }

    fun deleteAllZitaten(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteAllZitaten()
        }

    }
}