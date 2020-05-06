package com.example.rst_l3.lib.genetic

import java.lang.StringBuilder

class AlgorithmModel(
    var a: Int,
    var b: Int,
    var c: Int,
    var d: Int,
    var y: Int,
    var lowBound: Int,
    var highBound: Int,
    var samples: Int,
    var mutationsPercent: Double
)

class GeneticEntry(
    val x1: Int,
    val x2: Int,
    val x3: Int,
    val x4: Int,
    var diff: Int = 0,
    var adaptation: Double = 0.0
) : Comparable<GeneticEntry> {
    override fun compareTo(other: GeneticEntry): Int {
        return when {
            this.adaptation > other.adaptation -> 1
            this.adaptation < other.adaptation -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("X1 = ")
        builder.append(this.x1)
        builder.append("\n")
        builder.append("X2 = ")
        builder.append(this.x2)
        builder.append("\n")
        builder.append("X3 = ")
        builder.append(this.x3)
        builder.append("\n")
        builder.append("X4 = ")
        builder.append(this.x4)
        builder.append("\n")
        return builder.toString()
    }
}
