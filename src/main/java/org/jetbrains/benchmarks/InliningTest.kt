package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.Closeable

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class InliningTest {
}
