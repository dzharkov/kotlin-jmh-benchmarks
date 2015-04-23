package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner

import java.util.Random
import org.openjdk.jmh.infra.*


[State(Scope.Benchmark)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class SimpleSafeCallWithElvisBenchmark {
    data class Data(val x: Int)

    var size: Int = 1000000

    [Param("0.1", "0.5", "0.9")]
    var nullFrequency: Double = 0.0

    var data: Array<Data?> = Array(1) { Data(1) }
    private var step = 0

    Setup(Level.Iteration) fun init() {
        val random = Random(123)
        step = 0
        data = Array(size) {
            if (random.nextDouble() < nullFrequency) null else Data(random.nextInt())
        }
    }

    [Benchmark]
    public fun withElvis(): Int {
        step = (step + 1) % size
        val obj = data[step]
        return obj?.x ?: 1
    }

    [Benchmark]
    public fun withoutElvis(): Int {
        step = (step + 1) % size
        val obj = data[step]
        return if (obj != null) obj.x else 1
    }
}

