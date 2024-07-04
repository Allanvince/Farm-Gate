package com.vince.shambafirm.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddFarmViewModel : ViewModel() {

    private val _farmRecords = MutableLiveData<MutableList<HashMap<
            String, String>>>()
    val farmRecords: LiveData<MutableList<HashMap<
            String, String>>> get() = _farmRecords

    init {
        _farmRecords.value = mutableListOf()
    }
    fun setFarmRecords(records: HashMap<String, String>) {
        val currentRecords = _farmRecords.value ?: mutableListOf()
        currentRecords.add(records)
        _farmRecords.value = currentRecords
    }
}