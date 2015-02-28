package org.jetbrains.benchmarks.inline

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import java.util.*
import org.openjdk.jmh.infra.*

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class StdlibCollectionsInlineBenchmark {
    [Param("100", "10000", "1000000")]
    var size: Int = 0

    var intData = IntArray(1)

    Setup fun intInit() {
        val random = Random(123)
        intData = IntArray(size)
        for (i in intData.indices) {
            intData[i] = random.nextInt()
        }
    }

    [Benchmark]
    fun countBenchmark(): Int {
        val localData = intData
        return localData.count { it % 2 == 0 }
    }

    [Benchmark]
    fun countOSRViolationsBenchmark(bh: Blackhole) {
        val localData = intData
        bh.consume(localData.count { it % 2 == 0 })
    }

    [Benchmark]
    fun countIdealBenchmark(): Int {
        val localData = intData
        var count = 0
        for (element in localData) {
            if (element % 2 == 0) {
                count++
            }
        }
        return count
    }

    [Benchmark]
    fun filterBenchmark(): Any {
        val localData = intData
        return localData.filter { it % 2 == 0 }
    }

    [Benchmark]
    fun filterIdealBenchmark(): Any {
        val localData = intData
        var destination = arrayListOf<Int>()
        for (element in localData) {
            if (element % 2 == 0) {
                destination.add(element)
            }
        }
        return destination
    }

    [Benchmark]
    fun foldBenchmark(): Any {
        val localData = intData
        return localData.fold(0) { (x, y) -> x + y }
    }

    [Benchmark]
    fun foldIdealBenchmark(): Any {
        val localData = intData
        var accumulator = 0
        for (element in localData) {
            accumulator = accumulator + element
        }
        return accumulator
    }

    [Benchmark]
    fun intRangeCountBenchmark(): Int {
        return (0..size).count { it % 2 == 0 }
    }

    [Benchmark]
    fun intRangeCountBenchmarkIdeal(): Int {
        var count = 0
        for (element in 0..size) {
            if (element % 2 == 0) {
                count++
            }
        }
        return count
    }
}
