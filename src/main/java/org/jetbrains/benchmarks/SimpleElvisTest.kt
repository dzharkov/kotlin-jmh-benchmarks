package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner

import org.jetbrains.benchmarks.StarsKotlin.AVLTree.Node
import java.util.Random
import org.openjdk.jmh.infra.*


[State(Scope.Benchmark)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class SimpleElvisTestKotlin {
    data class Data(val x: Int)

    [Param("100", "1000", "1000000")]
    var size: Int = 0

    [Param("0.1", "0.5", "0.9")]
    var nullFrequency: Double = 0.0

    var data: Array<Data?> = Array(1) { Data(1) }

    Setup fun init() {
        val random = Random(123)
        data = Array(size) {
            if (random.nextDouble() < nullFrequency) null else Data(random.nextInt())
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    fun withElvisWork(obj: Data?) = obj?.x ?: 1

    [Benchmark]
    public fun withElvis(bh: Blackhole) {
        val d = data
        val size = this.size
        for (i in 0..size - 1) {
            val obj = d[i]
            bh.consume(withElvisWork(obj))
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    fun withoutElvisWork(obj: Data?) = if (obj != null) obj.x else 1

    [Benchmark]
    public fun withoutElvis(bh: Blackhole) {
        val d = data
        val size = this.size
        for (i in 0..size - 1) {
            val obj = d[i]
            bh.consume(withoutElvisWork(obj))
        }
    }
}

