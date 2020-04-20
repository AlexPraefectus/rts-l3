package com.example.rst_l3.lib

import kotlin.math.ceil
import kotlin.math.sqrt


fun factoriseFermat(n: Int): ArrayList<Int>{
    val res = ArrayList<Int>()
    // since Fermat's factorization applicable for odd positive integers only
    if (n <= 0) {
        res.add(n)
        return res
    }
    if (n % 2 == 0){
        res.add(n / 2)
        res.add(2)
        return res
    }

    var a = ceil(sqrt(n.toDouble())).toInt()
    // if n is a perfect root, then both its square roots are its factors
    if(a * a == n) {
        res.add(a)
        res.add(a)
        return res;
    }

    var b: Int
    while (true) {
        val b1 = a * a - n
        b = sqrt(b1.toDouble()).toInt()
        a += if (b * b == b1) break else 1
    }

    res.add(a + b)
    res.add(a - b)
    return res
}
