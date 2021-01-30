package com.example.meine_zitaten

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel


class ZitatenViewModel : ViewModel(){

    val zitaten = listOf(
        Zitate(
            "Alles was wir machen kommt in der Zukünft an uns selbst!",
            "Albert Einstein",
            "1899"
        ),
        Zitate(
            "Wenn man frühe aufsteht, kriegt man auch vieles",
            "Cokrat",
            "1488"
        ),
        Zitate(
            "Übung macht Maister",
            "Deutsche Rede Ausdrücke",
            "0000"
        )

    )

    private var index = 0

    private val _zitate = MutableLiveData<Zitate>().apply { value = zitaten[index] }
    val zitate: LiveData<Zitate>
        get() = _zitate
    val isFirst = Transformations.map(zitate) { index == 0 }
    val isLast = Transformations.map(zitate) { index == zitaten.size -1 }

    fun nextZitate() {
        if (index < zitaten.size -1) {

            index++
            _zitate.value = zitaten[index]
        }

    }

    fun previousZitate() {
        if (index > 0) {
            index--
            _zitate.value = zitaten[index]
        }
    }
}