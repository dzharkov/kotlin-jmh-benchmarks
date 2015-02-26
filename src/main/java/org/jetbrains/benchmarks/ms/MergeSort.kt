package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import org.jetbrains.benchmarks.ms.*

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open class MergeSortKotlin {
    public class object {
        public val size : Int = MergeSortJava.SIZE
    }
    val helper = IntArray(size)

    fun mergeSort(a : IntArray, from : Int, to : Int) {
        if (from >= to) {
            return
        }

        val m = (from + to) / 2

        mergeSort(a, from, m)
        mergeSort(a, m+1, to)

        var k = 0
        var i = from
        var j = m+1

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

    [Benchmark]
    fun testMethod() {
        val size = size
        val a = IntArray(size)
        for (i in 0..size-1) {
            a[i] = size-i
        }

        mergeSort(a, 0, size-1)

        for (i in 1..size-1) {
            if (a[i] < a[i-1]) {
                throw RuntimeException("fsdfds")
            }
        }
    }

}