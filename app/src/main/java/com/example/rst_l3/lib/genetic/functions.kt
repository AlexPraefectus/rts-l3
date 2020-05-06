package com.example.rst_l3.lib.genetic

import com.example.rst_l3.lib.genetic.stategies.AbstractStrategy
import kotlin.random.Random
import kotlin.math.abs

fun solve(am: AlgorithmModel, st: AbstractStrategy): GeneticEntry{
    val generator = Random.Default
    var population = arrayOfNulls<GeneticEntry>(am.samples)
    val replaceAmount: Int = (am.samples / 10)

    for (i in 0 until am.samples){
        population[i] = GeneticEntry(
            generator.nextInt(am.lowBound, am.highBound),
            generator.nextInt(am.lowBound, am.highBound),
            generator.nextInt(am.lowBound, am.highBound),
            generator.nextInt(am.lowBound, am.highBound)
        )
    }

    var best: GeneticEntry

    while (true) {
        var totalReverseDiff = 0.0
        best = population[0]!!
        var bestDiff: Int = Int.MAX_VALUE
        for (entry in population) {
            val func: Int = entry!!.x1 * am.a + entry.x2 * am.b + entry.x3 * am.c + entry.x4 * am.d
            val diff = abs(am.y - func)
            entry.diff = diff
            totalReverseDiff += 1.0 / diff
            if (diff < bestDiff) {
                bestDiff = diff
                best = entry
            }
            if (diff == 0) break
        }
        // if bestDiff == 0 then we found a solution and don't need further calculations
        if (bestDiff != 0) {
            for (entry in population) {
                entry!!.adaptation = 1.0 / entry.diff / totalReverseDiff
            }
        }
        else {
            break
        }
        population.sort()
        population = population.slice(replaceAmount .. population.lastIndex).toTypedArray()
        for (i in 0 until replaceAmount){
            val father = population.random()
            val mother = population.random()
            val child = GeneticEntry(father!!.x1, father.x2, mother!!.x3, mother.x4)
            population[population.lastIndex - replaceAmount + i] = if(generator.nextDouble(1.0) > am.mutationsPercent){
                st.mutate(child, am)
            }else {
                child
            }
        }
    }

    return best
}