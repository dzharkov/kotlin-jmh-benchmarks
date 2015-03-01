package org.jetbrains.benchmarks.inline

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import java.util.*
import org.openjdk.jmh.infra.*

open class A0
open class A1
open class A2
open class A3
open class A4
open class A5
open class A6
open class A7
open class A8
open class A9
open class A10
open class A11
open class A12
open class A13
open class A14
open class A15
open class A16
open class A17
open class A18
open class A19
open class A20
open class A21
open class A22
open class A23
open class A24
open class A25
open class A26
open class A27
open class A28
open class A29
open class A30
open class A31
open class A32
open class A33
open class A34
open class A35
open class A36
open class A37
open class A38
open class A39
open class A40
open class A41
open class A42
open class A43
open class A44
open class A45
open class A46
open class A47
open class A48
open class A49

inline fun <reified T> Array<*>.countIsInstance(): Int {
    return count { it !is T }
}

fun Array<*>.countIsInstance(klass: Class<*>): Int {
    return count { !klass.isInstance(it) }
}

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class CountsInstanceBenchmark {
    [Param("10000", "1000000")]
    var size = 1

    [Param("1", "2", "50")]
    var usedClasses = 1

    var data = array<Any>()

    Setup fun initObjects() {
        val random = Random(123)
        val classNamePrefix = javaClass<A0>().getName().replaceFirst(".$", "")
        data = Array(size) {
            Class.forName(classNamePrefix + (random.nextInt(usedClasses))).newInstance()!!
        }
    }

    [Benchmark]
    fun countIsInstanceReifiedBenchmark(): Int {
        val local = data
        return local.countIsInstance<A0>()
    }

    [Benchmark]
    fun countIsInstanceNonReifiedBenchmark(): Int {
        val local = data
        return local.countIsInstance(javaClass<A0>())
    }

    [Benchmark]
    fun countIsInstanceBaselineBenchmark(): Int {
        val local = data
        return local.count { it !is A0 }
    }
}