package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.GenerateMicroBenchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.logic.BlackHole
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.Closeable

inline fun doAlmostNothing(y : Int, block : () -> Int) : Int {
    return block() + y
}

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class InliningTest {
    [State(Scope.Benchmark)]
    public var one1 : Int = 1
    [State(Scope.Benchmark)]
    public var one2 : Int = 1

    [GenerateMicroBenchmark]
    fun inliningIdeal() : Int {
        val x = one1
        return x + one2 + 1
    }

    [GenerateMicroBenchmark]
    fun valInlining() : Int {
        val x = one1
        return doAlmostNothing(1) {
            x + one2
        }
    }
}
