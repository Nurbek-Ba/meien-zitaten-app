package com.example.meine_zitaten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class ZitatenViewModel : ViewModel(){

    private val _zitaten = MutableLiveData <MutableList<Zitate>>().apply {
        value = mutableListOf()
    }

    val zitaten: LiveData<MutableList<Zitate>>
        get() = _zitaten

    val hasNoZitaten = Transformations.map(zitaten) { zitaten.value.isNullOrEmpty()}

    var newZitateAdded = false

    fun createZitate(text: String, author: String, year: String) {

        newZitateAdded = true

        val zitate = Zitate(text, author, year)
        val list = _zitaten.value?: mutableListOf()
        list.add(zitate)
        _zitaten.value = list
    }

}