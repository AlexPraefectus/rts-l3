package com.example.rst_l3.ui.lab_32

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LAB32ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LAB3.2 Fragment"
    }
    val text: LiveData<String> = _text
}