package com.example.rst_l3.lib.genetic.stategies

import com.example.rst_l3.lib.genetic.AlgorithmModel
import com.example.rst_l3.lib.genetic.GeneticEntry
import kotlin.random.Random


abstract class AbstractStrategy {
    abstract val handles: String
    abstract val description: String

    val random: Random = Random.Default
    abstract fun mutate(entry: GeneticEntry, model: AlgorithmModel): GeneticEntry
}