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
open public class SimpleElvisTestKotlin() {

    [GenerateMicroBenchmark]
    public fun solve1(bh : BlackHole)  {
        var q = ElvisTestData.count
        while (q-- > 0)
        {
            val instance = ElvisTestData.nextInstance()
            val instance1 = ElvisTestData.nextInstance()
            bh.consume((instance?.value ?: 1) * (instance1?.value ?: 5))
        }
    }

    [GenerateMicroBenchmark]
    public fun solve2(bh : BlackHole)  {
        var q = ElvisTestData.count
        while (q-- > 0)
        {
            val instance = ElvisTestData.nextInstance()
            val instance1 = ElvisTestData.nextInstance()
            bh.consume((if (instance != null) instance.value else 1) * (if (instance1 != null) instance1.value else 5))
        }
    }
}
