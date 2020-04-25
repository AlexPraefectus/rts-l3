package com.example.rst_l3.lib

import android.content.Context
import android.view.Gravity
import android.widget.Switch
import android.widget.TableRow
import android.widget.TextView
import java.lang.StringBuilder
import java.util.concurrent.*

class Dot(var x1: Int, var x2: Int, var greater: Boolean, val name: String){
    override fun toString(): String {
        val b = StringBuilder()
        b.append(name)
        b.append(" (")
        b.append(x1)
        b.append(", ")
        b.append(x2)
        b.append(")")
        return b.toString()
    }

    fun toTableRow(context: Context): TableRow {
        val row = TableRow(context)
        row.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

        val tv = TextView(context)
        tv.text = this.toString()
        tv.textSize = 16.0f
        row.addView(tv)

        val s = Switch(context)
        s.gravity = Gravity.END
        s.text = ""
        row.addView(s)
        return row
    }
}

class Weights(var w1: Double, var w2: Double)


class EndlessChain<T>(val list: List<T>) : Iterable<T> {
    private var iterIdx = 0
    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {
            override fun hasNext(): Boolean {
                return list.isNotEmpty()
            }

            override fun next(): T {
                val ret = list[iterIdx++]
                if (iterIdx == list.lastIndex) {
                    iterIdx = 0
                }
                return ret
            }
        }
    }
}

/**
 * @param timeoutMillis max time for perceptron to learn
 * @param iterationsLimit max iteration of learning
 * @param learningSpeed coefficient (commonly small) to change weights
 * @param dots dots to train the perceptron
 * @param triggerThreshold threshold to compare result with
 */
fun perceptron(
    timeoutMillis: Int?,
    iterationsLimit: Int?,
    learningSpeed: Double,
    dots: List<Dot>,
    triggerThreshold: Double
): Weights {
    assert(timeoutMillis != null || iterationsLimit != null)
    val executor: ExecutorService = Executors.newSingleThreadExecutor()
    val intermediateWeights = Weights(0.0, 0.0)
    val chained = EndlessChain(dots).iterator()
    val future: Future<Boolean> = executor.submit(Callable<Boolean> {
        var i = 0
        var correct = 0
        while (true) {
            val dot = chained.next()
            val res = intermediateWeights.w1 * dot.x1 + intermediateWeights.w2 * dot.x2
            var inc: Boolean
            inc = if (dot.greater) {
                res > triggerThreshold
            } else {
                res < triggerThreshold
            }
            if (inc){
                correct += 1
            } else {
                correct = 0
                val diff = triggerThreshold - res
                intermediateWeights.w1 += diff * dot.x1 * learningSpeed
                intermediateWeights.w2 += diff * dot.x2 * learningSpeed
            }
            if (Thread.interrupted() || (iterationsLimit != null && ++i > iterationsLimit || correct == dots.size)) break
        }
        true
    })

    if (timeoutMillis != null) {
        try {
            future.get(timeoutMillis.toLong(), TimeUnit.MILLISECONDS)
        } catch (e: TimeoutException) {
            future.cancel(true)
        }
    } else {
        future.get()
    }

    return intermediateWeights
}