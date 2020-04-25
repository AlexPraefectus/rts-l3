package com.example.rst_l3.ui.lab_32

import android.widget.TableLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rst_l3.lib.Dot
import com.example.rst_l3.lib.Weights
import com.example.rst_l3.lib.perceptron
import java.lang.StringBuilder
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class Constants{
    fun getDots(): ArrayList<Dot>{
        val res = ArrayList<Dot>();
        res.add(Dot(0, 6, true, "A"))
        res.add(Dot(1, 5, true, "B"))
        res.add(Dot(3, 3, true, "C"))
        res.add(Dot(2, 4, true, "D"))
        return res
    }

    val triggerThreshold = 4.0
}

class LAB32ViewModel : ViewModel() {
    private val c = Constants()
    private val _text = MutableLiveData<String>().apply { value = "LAB3.2 - Perceptrones" }
    val text: LiveData<String> = _text

    private val _useIterations = MutableLiveData<Boolean>().apply { value = false }
    fun setUseIterations(isChecked: Boolean) {
        _useIterations.apply { value = isChecked }
    }

    private val _learningSpeed = MutableLiveData<Double>()
    fun setLearningSpeed(s: Double){ _learningSpeed.apply { value = s } }


    private val _deadline = MutableLiveData<Double>()
    fun setDeadline(d: Double) { _deadline.apply { value = d }}

    private val _iterations = MutableLiveData<Int>()
    fun setIterations(i: Int) { _iterations.apply { value = i }}

    private val _dots = MutableLiveData<ArrayList<Dot>>().apply { value = c.getDots() }
    val dots: LiveData<ArrayList<Dot>> = _dots

    private val _resText = MutableLiveData<String>();
    val resText: LiveData<String> = _resText

    private fun postTraining() {
        _resText.postValue("Model is training...")
    }
    private fun postDone(w: Weights){
        val b = StringBuilder()
        b.append("Training results:\n")
        b.append("W1: ")
        b.append(w.w1)
        b.append("\n")
        b.append("W2: ")
        b.append(w.w2)
        b.append("\n")
        _resText.postValue(b.toString())
    }

    private val lock = ReentrantLock()
    fun train(dotGts: List<Boolean>){
        assert(dotGts.size == _dots.value!!.size)
        for (i in dots.value!!.indices){
            dots.value!![i].greater = dotGts[i]
        }
        if (lock.tryLock(0, TimeUnit.SECONDS)){
            thread {
                postTraining()
                val weights = if (_useIterations.value!!) {
                    perceptron(
                        timeoutMillis = null,
                        iterationsLimit = _iterations.value!!,
                        learningSpeed = _learningSpeed.value!!,
                        dots = _dots.value!!,
                        triggerThreshold = c.triggerThreshold
                    )
                } else {
                    perceptron(
                        timeoutMillis = (_deadline.value!! * 1000).toInt(),
                        iterationsLimit = null,
                        learningSpeed = _learningSpeed.value!!,
                        dots = _dots.value!!,
                        triggerThreshold = c.triggerThreshold
                    )
                }
                postDone(weights)
            }
            lock.unlock()
        }
    }
}
