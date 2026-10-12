package ru.mirea.kornilov.fragmentmanagerapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel : ViewModel() {
    private val selectedItem = MutableLiveData<Country?>()

    fun selectItem(item: Country) {
        selectedItem.value = item
    }

    fun getSelectedItem(): LiveData<Country?> = selectedItem
}
