package org.jetbrains.benchmarks.ms

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder

import java.util.Random
import java.util.concurrent.TimeUnit

State(Scope.Thread)
BenchmarkMode(Mode.AverageTime)
OutputTimeUnit(TimeUnit.NANOSECONDS)
open public class MergeSortKotlinBenchmark {
    Param("100", "1000", "1000000")
    var size: Int = 0

    private var data: IntArray = IntArray(0)
    private var helper: IntArray = IntArray(1)

    Setup
    public fun init() {
        helper = IntArray(size)
        data = IntArray(size)
        val random = Random(123)
        for (i in 0..size - 1) {
            data[i] = random.nextInt()
        }
    }

    private fun mergeSort(a: IntArray, from: Int, to: Int) {
        if (from >= to) {
            return
        }

        val m = (from + to) / 2

        mergeSort(a, from, m)
        mergeSort(a, m + 1, to)

        var k = 0
        var i = from
        var j = m + 1

        while (i <= m && j <= to) {
            if (a[i] < a[j]) {
                helper[k++] = a[i++]
            } else {
                helper[k++] = a[j++]
            }
        }

        while (i <= m) {
            helper[k++] = a[i++]
        }

        while (j <= to) {
            helper[k++] = a[j++]
        }

        System.arraycopy(helper, 0, a, from, k)
    }

    Benchmark
    public fun benchmarkMethod(bh: Blackhole) {
        val a = data
        val size = this.size
        mergeSort(a, 0, size - 1)

        bh.consume(a)
    }
}
