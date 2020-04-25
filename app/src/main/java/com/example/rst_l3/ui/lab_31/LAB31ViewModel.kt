package com.example.rst_l3.ui.lab_31

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rst_l3.lib.factoriseFermat

class LAB31ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply { value = "Fermat's factorization method" }
    val text: LiveData<String> = _text

    private val _factorisable = MutableLiveData<Int>().apply { value = 0; }
    private val _factorisationRes = MutableLiveData<List<Int>>()
    private val _factorisationResText = MutableLiveData<String>().apply { value = "" }
    private val _iterationsSpent = MutableLiveData<Int>().apply { value = 0 }
    private val iterationsSpent: LiveData<Int> = _iterationsSpent

    /**
     * returns true if we can set factorisable from given input, false otherwise
     */
    fun setFactorisable(s : String): String?{
        _factorisationResText.apply { value = "" }
        val num: Int
        try {
            num = s.toInt()
        }catch (e: NumberFormatException){
            return "Wrong Number Format!";
        }

        if (num <= 0) return "Only positive numbers are supported!"

        _factorisable.apply { value = num; }
        return null;
    }

    fun getFactorisationResText(): LiveData<String> { return _factorisationResText }

    private fun buildFactorisationResText(){
        val builder = StringBuilder();
        builder.append("Result:")
        builder.append("\n")
        for (i in this._factorisationRes.value!!){
            builder.append(i.toString())
            builder.append("\n")
        }
        builder.append("Iterations spend:\n")
        builder.append(iterationsSpent.value)
        builder.append("\n")
        _factorisationResText.apply { value = builder.toString() }
    }

    fun factorise(){
        val res = _factorisable.value?.let { factoriseFermat(it) }!!
        _factorisationRes.apply { value = res.res }
        _iterationsSpent.apply { value = res.iter }
        buildFactorisationResText()
    }

}