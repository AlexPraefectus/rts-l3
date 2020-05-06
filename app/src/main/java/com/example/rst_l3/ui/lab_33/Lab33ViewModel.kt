package com.example.rst_l3.ui.lab_33

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rst_l3.lib.genetic.AlgorithmModel
import com.example.rst_l3.lib.genetic.GeneticEntry
import com.example.rst_l3.lib.genetic.solve
import com.example.rst_l3.lib.genetic.stategies.AbstractStrategy
import com.example.rst_l3.lib.genetic.stategies.Registry
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class Lab33ViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Lab 3.3 Genetic Algorithm"
    }
    val text: LiveData<String> = _text

    val registry = Registry.Default

    private val _y = MutableLiveData<Int>().apply { value = 10 }
    val y: LiveData<Int> = _y

    private val _a = MutableLiveData<Int>().apply { value = 1 }
    val a: LiveData<Int> = _a

    private val _b = MutableLiveData<Int>().apply { value = 1 }
    val b: LiveData<Int> = _b

    private val _c = MutableLiveData<Int>().apply { value = 1 }
    val c: LiveData<Int> = _c

    private val _d = MutableLiveData<Int>().apply { value = 1 }
    val d: LiveData<Int> = _d

    private val _mutations = MutableLiveData<Double>().apply { value = 0.01 }
    val mutations: LiveData<Double> = _mutations

    private val _samples = MutableLiveData<Int>().apply { value = 50 }
    val samples: LiveData<Int> = _samples

    private val _strategy = MutableLiveData<AbstractStrategy>()
    val strategy: LiveData<AbstractStrategy> = _strategy

    val lowBound = MutableLiveData<Int>().apply { value = 0 }
    val highBound = MutableLiveData<Int>().apply { value = 5 }

    private val _responseText = MutableLiveData<String>()
    val responseText: LiveData<String> = _responseText

    var algorithmModel = AlgorithmModel(
        a = _a.value!!,
        b = _b.value!!,
        c = _c.value!!,
        d = _d.value!!,
        y = _y.value!!,
        lowBound = lowBound.value!!,
        highBound = highBound.value!!,
        mutationsPercent = _mutations.value!!,
        samples = _samples.value!!
    )

    private fun updateModel(){
        algorithmModel = AlgorithmModel(
            a = _a.value!!,
            b = _b.value!!,
            c = _c.value!!,
            d = _d.value!!,
            y = _y.value!!,
            lowBound = lowBound.value!!,
            highBound = highBound.value!!,
            mutationsPercent = _mutations.value!!,
            samples = _samples.value!!
        )
    }
    fun setY(y: Int){
        _y.value = y
        updateModel()
    }

    fun setA(a: Int){
        _a.value = a
        updateModel()
    }

    fun setB(b: Int){
        _b.value = b
        updateModel()
    }

    fun setC(c: Int){
        _c.value = c
        updateModel()
    }

    fun setD(d: Int){
        _d.value = d
        updateModel()
    }

    fun setStrategy(s: String) {
        _strategy.value = registry.pick(s)
    }

    fun setSamples(samples: Int){
        _samples.value = samples
        updateModel()
    }

    fun setMutations(mutations: Double){
        _mutations.value = mutations
        updateModel()
    }

    fun setBounds(bounds: String){
        val splitted = bounds.split("|")
        lowBound.value = (splitted[0].toDouble() * _y.value!!).toInt()
        highBound.value = (splitted[1].toDouble() * _y.value!!).toInt()
        updateModel()
    }
    private val solution = MutableLiveData<GeneticEntry>()
    private val singleCalculationsLock = ReentrantLock()

    fun calculate(){
        if (singleCalculationsLock.tryLock(0, TimeUnit.SECONDS)){
            solution.postValue(null)
            _responseText.postValue("Solving ...")

/*            thread {
                val start = System.currentTimeMillis()
                while (true){
                    if (solution.value == null) {
                        Thread.sleep(500)
                        val builder = StringBuilder()
                        builder.append("Solving ... \n")
                        builder.append("Spent: ")
                        builder.append(System.currentTimeMillis() - start)
                        builder.append(" ms\n")
                        _responseText.postValue(builder.toString())
                    }else{
                        break
                    }
                }
            }*/

            thread {
                val solutionTmp = solve(algorithmModel, _strategy.value!!)
                solution.postValue(solutionTmp)
                val builder = StringBuilder()
                builder.append("Solution found:\n")
                builder.append(solutionTmp)
                Thread.sleep(50)
                _responseText.postValue(builder.toString())
            }
            singleCalculationsLock.unlock()
        }
    }
}
