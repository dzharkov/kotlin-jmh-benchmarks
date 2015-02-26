package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class InliningTest {
}
