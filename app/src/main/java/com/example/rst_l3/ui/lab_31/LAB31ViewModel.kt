package com.example.rst_l3.ui.lab_31

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LAB31ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Fermat's factorization method"
    }
    val text: LiveData<String> = _text
}