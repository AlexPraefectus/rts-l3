package com.example.rst_l3.ui.lab_33

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Lab33ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is LAB3.3 Fragment"
    }
    val text: LiveData<String> = _text
}
