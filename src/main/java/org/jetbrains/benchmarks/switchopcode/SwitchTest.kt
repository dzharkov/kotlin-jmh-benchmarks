package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.infra.*

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
public open class SwitchTestKotlin() {
    [Param("100", "1000", "1000000")]
    var iterations: Int = 0

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun sparseSwitch(u : Int): Int {
        when (u) {
            1 -> {
                return 1
            }
            100500 -> {
                return 2
            }
            2 -> {
                return 3
            }
            3 -> {
                return 4
            }
            4 -> {
                return 5
            }
            5 -> {
                return 6
            }
            6 -> {
                return 7
            }
            7 -> {
                return 1
            }
            8 -> {
                return 9
            }
            9 -> {
                return 1
            }
            10 -> {
                return 2
            }
            11 -> {
                return 3
            }
            12 -> {
                return 4
            }
            13 -> {
                return 4
            }
            14 -> {
                return 4
            }
            15 -> {
                return 435
            }
            16 -> {
                return 31
            }
            17 -> {
                return 1
            }
            18 -> {
                return 1
            }
            19 -> {
                return 1
            }
            20 -> {
                return 1
            }
            else -> {
                return 5
            }
        }
    }

    [Benchmark]
    public open fun testSparseSwitch(bh : Blackhole) {
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(sparseSwitch(i))
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun denseSwitch(u : Int): Int {
        when (u) {
            1 -> {
                return 1
            }
            -1 -> {
                return 2
            }
            2 -> {
                return 3
            }
            3 -> {
                return 4
            }
            4 -> {
                return 5
            }
            5 -> {
                return 6
            }
            6 -> {
                return 7
            }
            7 -> {
                return 1
            }
            8 -> {
                return 9
            }
            9 -> {
                return 1
            }
            10 -> {
                return 2
            }
            11 -> {
                return 3
            }
            12 -> {
                return 4
            }
            13 -> {
                return 4
            }
            14 -> {
                return 4
            }
            15 -> {
                return 435
            }
            16 -> {
                return 31
            }
            17 -> {
                return 1
            }
            18 -> {
                return 1
            }
            19 -> {
                return 1
            }
            20 -> {
                return 1
            }
            else -> {
                return 5
            }
        }
    }
    [Benchmark]
    public open fun testDenseSwitch(bh : Blackhole) {
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(denseSwitch(i))
        }
    }

}

