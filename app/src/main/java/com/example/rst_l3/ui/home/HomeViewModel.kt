package com.example.rst_l3.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _header1 = MutableLiveData<String>().apply {
        value = "RTS course, lab3"
    }
    private val _header2 = MutableLiveData<String>().apply {
        value = "Developed by Korienev Oleksandr"
    }
    private val _header3 = MutableLiveData<String>().apply {
        value = "Student of academic group IV-72"
    }

    val header1: LiveData<String> = _header1
    val header2: LiveData<String> = _header2
    val header3: LiveData<String> = _header3
}