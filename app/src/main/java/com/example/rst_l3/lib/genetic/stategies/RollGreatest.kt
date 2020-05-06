package com.example.rst_l3.lib.genetic.stategies

import com.example.rst_l3.lib.genetic.AlgorithmModel
import com.example.rst_l3.lib.genetic.GeneticEntry
import kotlin.math.max

class RollGreatest : AbstractStrategy() {
    override val handles = "roll_greatest"
    override val description =
        "Pick the biggest value\nAnd roll it using current bounds\nIf many values are equals\nThe first is picked"

    override fun mutate(entry: GeneticEntry, model: AlgorithmModel): GeneticEntry {
        when (max(entry.x1, max(entry.x2, max(entry.x3, entry.x4)))) {
            entry.x1 -> return GeneticEntry(
                this.random.nextInt(model.lowBound, model.highBound),
                entry.x2,
                entry.x3,
                entry.x4
            )
            entry.x2 -> return GeneticEntry(
                entry.x1,
                this.random.nextInt(model.lowBound, model.highBound),
                entry.x3,
                entry.x4
            )
            entry.x3 -> return GeneticEntry(
                entry.x1,
                entry.x2,
                this.random.nextInt(model.lowBound, model.highBound),
                entry.x4
            )
            entry.x4 -> return GeneticEntry(
                entry.x1,
                entry.x2,
                entry.x3,
                this.random.nextInt(model.lowBound, model.highBound)
            )
            else -> return GeneticEntry(entry.x1, entry.x2, entry.x3, entry.x4)
        }
    }
}