package com.example.rst_l3.lib

import kotlin.math.ceil
import kotlin.math.sqrt

class FactorisationRes(val res: ArrayList<Int>, var iter: Int = 0)

fun factoriseFermat(n: Int): FactorisationRes{
    val res = ArrayList<Int>()
    val resFull = FactorisationRes(res)
    // since Fermat's factorization applicable for odd positive integers only
    if (n <= 0) {
        res.add(n)
        return resFull
    }
    if (n % 2 == 0){
        res.add(n / 2)
        res.add(2)
        return resFull
    }

    var a = ceil(sqrt(n.toDouble())).toInt()
    // if n is a perfect root, then both its square roots are its factors
    if(a * a == n) {
        res.add(a)
        res.add(a)
        return resFull;
    }
    var iterations = 0;
    var b: Int
    while (true) {
        iterations++
        val b1 = a * a - n
        b = sqrt(b1.toDouble()).toInt()
        a += if (b * b == b1) break else 1
    }

    res.add(a + b)
    res.add(a - b)
    resFull.iter = iterations
    return resFull
}
