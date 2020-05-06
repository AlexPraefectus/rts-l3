package com.example.rst_l3.lib.genetic.stategies

class Registry(private val default: AbstractStrategy) {
    private val strategies: ArrayList<AbstractStrategy> = ArrayList()

    init {
        strategies.add(default)
    }

    fun add(strategy: AbstractStrategy) {
        strategies.add(strategy)
    }

    fun pick(handles: String): AbstractStrategy {
        for (s in strategies) {
            if (s.handles == handles) return s
        }
        return default
    }

    companion object {
        val Default: Registry
            get() {
                val r = Registry(RollGreatest())
                r.add(RollLeast())
                return r
            }
    }
}