package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner

import org.jetbrains.benchmarks.StarsKotlin.AVLTree.Node
import java.util.Random


/**
 * @author Denis Zharkov
 */
[State(Scope.Thread)]
[BenchmarkMode(Mode.All) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class SimpleElvisTestKotlin() {
    [State(Scope.Benchmark)]
    public var array : Array<ElvisTestData?> = Array<ElvisTestData?>(ElvisTestData.arraySize, { null; })

    [State(Scope.Benchmark)]
    public var steps : IntArray = IntArray(2 * array.size)

    [State(Scope.Benchmark)]
    public var result : Int = 0
    [State(Scope.Benchmark)]
    public var step : Int = 0
    [State(Scope.Benchmark)]
    public var random : Random = Random(123)

    [Setup]
    public fun init() {
        random = Random(123)
        for (i in 1..array.size-1) {
            array[i] = if (random.nextInt(1000) < 900) null else ElvisTestData(random.nextInt())
        }
        for (i in 0..steps.size-1) {
            steps[i] = random.nextInt(array.size - 1)
        }

        step = 0
    }

    [GenerateMicroBenchmark]
    public fun withElvis(bh: BlackHole) {
        if (step > steps.size - 2) {
            step = 0
        }
        var next = steps[step++]
        val obj = array[next]
        bh.consume(obj?.value ?: steps[step++])
    }

    [GenerateMicroBenchmark]
    public fun withoutElvis(bh: BlackHole) {
        if (step > steps.size - 2) {
            step = 0
        }
        var next = steps[step++]
        val obj = array[next]
        bh.consume(if (obj != null) obj.value else steps[step++] )
    }

    public fun main(args: Array<String>) {
        val opt = OptionsBuilder().include(".*" + javaClass<SimpleElvisTestKotlin>().getSimpleName() + ".*")?.forks(1)?.build()

        Runner(opt).run()
    }
}

