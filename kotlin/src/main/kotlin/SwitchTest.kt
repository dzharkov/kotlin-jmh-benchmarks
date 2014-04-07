package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole

import java.util.Comparator
import java.util.concurrent.TimeUnit

/**
 * @author Denis Zharkov
 */
[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
public open class SwitchTestKotlin() {
    open fun sparseSwitch(u : Int) : Int {
        var t : Int
        when (u) {
            1 -> {
                t = 1
            }
            100500 -> {
                t = 2
            }
            2 -> {
                t = 3
            }
            3 -> {
                t = 4
            }
            4 -> {
                t = 5
            }
            5 -> {
                t = 6
            }
            6 -> {
                t = 7
            }
            7 -> {
                t = 1
            }
            8 -> {
                t = 9
            }
            9 -> {
                t = 1
            }
            10 -> {
                t = 2
            }
            11 -> {
                t = 3
            }
            12 -> {
                t = 4
            }
            13 -> {
                t = 4
            }
            14 -> {
                t = 4
            }
            15 -> {
                t = 435
            }
            16 -> {
                t = 31
            }
            17 -> {
                t = 1
            }
            18 -> {
                t = 1
            }
            19 -> {
                t = 1
            }
            20 -> {
                t = 1
            }
            else -> {
                t = 5
            }
        }
        return t
    }

    [GenerateMicroBenchmark]
    public open fun testSparseSwitch(bh : BlackHole) {
        val n = ITERATIONS
        for (i in 0..n - 1) {
            bh.consume(sparseSwitch(i))
        }
    }

    open fun denseSwitch(u : Int) : Int {
        var t : Int
        when (u) {
            1 -> {
                t = 1
            }
            -1 -> {
                t = 2
            }
            2 -> {
                t = 3
            }
            3 -> {
                t = 4
            }
            4 -> {
                t = 5
            }
            5 -> {
                t = 6
            }
            6 -> {
                t = 7
            }
            7 -> {
                t = 1
            }
            8 -> {
                t = 9
            }
            9 -> {
                t = 1
            }
            10 -> {
                t = 2
            }
            11 -> {
                t = 3
            }
            12 -> {
                t = 4
            }
            13 -> {
                t = 4
            }
            14 -> {
                t = 4
            }
            15 -> {
                t = 435
            }
            16 -> {
                t = 31
            }
            17 -> {
                t = 1
            }
            18 -> {
                t = 1
            }
            19 -> {
                t = 1
            }
            20 -> {
                t = 1
            }
            else -> {
                t = 5
            }
        }
        return t
    }
    [GenerateMicroBenchmark]
    public open fun testDenseSwitch(bh : BlackHole) {
        val n = ITERATIONS
        for (i in 0..n - 1) {
            bh.consume(denseSwitch(i))
        }
    }

    class object {
        public val ITERATIONS : Int = SwitchTestJava.ITERATIONS
    }
}

