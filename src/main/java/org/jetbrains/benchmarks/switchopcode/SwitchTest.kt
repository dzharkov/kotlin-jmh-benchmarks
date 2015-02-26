package org.jetbrains.benchmarks.switchopcode

import org.openjdk.jmh.annotations.*;

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.infra.*

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
public open class SwitchTestKotlin() {
    [Param("100", "1000", "1000000")]
    var iterations: Int = 0

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun sparseSwitch(u : Int): Int {
        when (u) {
            1 -> {
                return 1
            }
            100500 -> {
                return 2
            }
            2 -> {
                return 3
            }
            3 -> {
                return 4
            }
            4 -> {
                return 5
            }
            5 -> {
                return 6
            }
            6 -> {
                return 7
            }
            7 -> {
                return 1
            }
            8 -> {
                return 9
            }
            9 -> {
                return 1
            }
            10 -> {
                return 2
            }
            11 -> {
                return 3
            }
            12 -> {
                return 4
            }
            13 -> {
                return 4
            }
            14 -> {
                return 4
            }
            15 -> {
                return 435
            }
            16 -> {
                return 31
            }
            17 -> {
                return 1
            }
            18 -> {
                return 1
            }
            19 -> {
                return 1
            }
            20 -> {
                return 1
            }
            else -> {
                return 5
            }
        }
    }

    [Benchmark]
    public open fun testSparseSwitch(bh : Blackhole) {
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(sparseSwitch(i))
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun denseSwitch(u : Int): Int {
        when (u) {
            1 -> {
                return 1
            }
            -1 -> {
                return 2
            }
            2 -> {
                return 3
            }
            3 -> {
                return 4
            }
            4 -> {
                return 5
            }
            5 -> {
                return 6
            }
            6 -> {
                return 7
            }
            7 -> {
                return 1
            }
            8 -> {
                return 9
            }
            9 -> {
                return 1
            }
            10 -> {
                return 2
            }
            11 -> {
                return 3
            }
            12 -> {
                return 4
            }
            13 -> {
                return 4
            }
            14 -> {
                return 4
            }
            15 -> {
                return 435
            }
            16 -> {
                return 31
            }
            17 -> {
                return 1
            }
            18 -> {
                return 1
            }
            19 -> {
                return 1
            }
            20 -> {
                return 1
            }
            else -> {
                return 5
            }
        }
    }
    [Benchmark]
    public open fun testDenseSwitch(bh : Blackhole) {
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(denseSwitch(i))
        }
    }

    var enums: Array<MyEnum> = array()

    Setup
    public fun initEnums() {
        enums = Array(iterations) {
            i -> MyEnum.values()[i % MyEnum.values().size]
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun enumSwitch(x: MyEnum): Int {
        when (x) {
            MyEnum.ITEM2 -> return 1
            MyEnum.ITEM4 -> return 2
            MyEnum.ITEM6 -> return 3
            MyEnum.ITEM8 -> return 4
            MyEnum.ITEM10 -> return 5
            MyEnum.ITEM12 -> return 6
            MyEnum.ITEM14 -> return 7
            MyEnum.ITEM16 -> return 8
            MyEnum.ITEM18 -> return 9
            MyEnum.ITEM20 -> return 10
            MyEnum.ITEM22 -> return 11
            MyEnum.ITEM24 -> return 12
            MyEnum.ITEM26 -> return 13
            MyEnum.ITEM28 -> return 14
            MyEnum.ITEM30 -> return 15
            MyEnum.ITEM32 -> return 16
            MyEnum.ITEM34 -> return 17
            MyEnum.ITEM36 -> return 18
            MyEnum.ITEM38 -> return 19
            MyEnum.ITEM40 -> return 20
            MyEnum.ITEM42 -> return 21
            MyEnum.ITEM44 -> return 22
            MyEnum.ITEM46 -> return 23
            MyEnum.ITEM48 -> return 24
            MyEnum.ITEM50 -> return 25
            MyEnum.ITEM52 -> return 26
            MyEnum.ITEM54 -> return 27
            MyEnum.ITEM56 -> return 28
            MyEnum.ITEM58 -> return 29
            MyEnum.ITEM60 -> return 30
            MyEnum.ITEM62 -> return 31
            MyEnum.ITEM64 -> return 32
            MyEnum.ITEM66 -> return 33
            MyEnum.ITEM68 -> return 34
            MyEnum.ITEM70 -> return 35
            MyEnum.ITEM72 -> return 36
            MyEnum.ITEM74 -> return 37
            MyEnum.ITEM76 -> return 38
            MyEnum.ITEM78 -> return 39
            MyEnum.ITEM80 -> return 40
            MyEnum.ITEM82 -> return 41
            MyEnum.ITEM84 -> return 42
            MyEnum.ITEM86 -> return 43
            MyEnum.ITEM88 -> return 44
            MyEnum.ITEM90 -> return 45
            MyEnum.ITEM92 -> return 46
            MyEnum.ITEM94 -> return 47
            MyEnum.ITEM96 -> return 48
            MyEnum.ITEM98 -> return 49
            MyEnum.ITEM100 -> return 50
            else -> return -1
        }
    }

    Benchmark
    public fun testEnumSwitch(bh: Blackhole) {
        val localEnums = enums
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(enumSwitch(localEnums[i]))
        }
    }

    var strings: Array<String> = array()

    Setup
    public fun initStrings() {
        strings = Array(iterations) { i ->
            "ABCDEFGHIJKLMNO${((i % 50) + 1)}"
        }
    }

    [CompilerControl(CompilerControl.Mode.DONT_INLINE)]
    private fun stringSwitch(s: String): Int {
        when (s) {
            "ABCDEFGHIJKLMNO1" -> return 1
            "ABCDEFGHIJKLMNO2" -> return 2
            "ABCDEFGHIJKLMNO3" -> return 3
            "ABCDEFGHIJKLMNO4" -> return 4
            "ABCDEFGHIJKLMNO5" -> return 5
            "ABCDEFGHIJKLMNO6" -> return 6
            "ABCDEFGHIJKLMNO7" -> return 7
            "ABCDEFGHIJKLMNO8" -> return 8
            "ABCDEFGHIJKLMNO9" -> return 9
            "ABCDEFGHIJKLMNO10" -> return 10
            "ABCDEFGHIJKLMNO11" -> return 11
            "ABCDEFGHIJKLMNO12" -> return 12
            "ABCDEFGHIJKLMNO13" -> return 13
            "ABCDEFGHIJKLMNO14" -> return 14
            "ABCDEFGHIJKLMNO15" -> return 15
            "ABCDEFGHIJKLMNO16" -> return 16
            "ABCDEFGHIJKLMNO17" -> return 17
            "ABCDEFGHIJKLMNO18" -> return 18
            "ABCDEFGHIJKLMNO19" -> return 19
            "ABCDEFGHIJKLMNO20" -> return 20
            "ABCDEFGHIJKLMNO21" -> return 21
            "ABCDEFGHIJKLMNO22" -> return 22
            "ABCDEFGHIJKLMNO23" -> return 23
            "ABCDEFGHIJKLMNO24" -> return 24
            "ABCDEFGHIJKLMNO25" -> return 25
            "ABCDEFGHIJKLMNO26" -> return 26
            "ABCDEFGHIJKLMNO27" -> return 27
            "ABCDEFGHIJKLMNO28" -> return 28
            "ABCDEFGHIJKLMNO29" -> return 29
            "ABCDEFGHIJKLMNO30" -> return 30
            "ABCDEFGHIJKLMNO31" -> return 31
            "ABCDEFGHIJKLMNO32" -> return 32
            "ABCDEFGHIJKLMNO33" -> return 33
            "ABCDEFGHIJKLMNO34" -> return 34
            "ABCDEFGHIJKLMNO35" -> return 35
            "ABCDEFGHIJKLMNO36" -> return 36
            "ABCDEFGHIJKLMNO37" -> return 37
            "ABCDEFGHIJKLMNO38" -> return 38
            "ABCDEFGHIJKLMNO39" -> return 39
            "ABCDEFGHIJKLMNO40" -> return 40
            "ABCDEFGHIJKLMNO41" -> return 41
            "ABCDEFGHIJKLMNO42" -> return 42
            "ABCDEFGHIJKLMNO43" -> return 43
            "ABCDEFGHIJKLMNO44" -> return 44
            "ABCDEFGHIJKLMNO45" -> return 45
            "ABCDEFGHIJKLMNO46" -> return 46
            "ABCDEFGHIJKLMNO47" -> return 47
            "ABCDEFGHIJKLMNO48" -> return 48
            "ABCDEFGHIJKLMNO49" -> return 49
            "ABCDEFGHIJKLMNO50" -> return 50
            else -> return -1
        }
    }

    Benchmark
    public fun testStringSwitch(bh: Blackhole) {
        val localStrings = strings
        val n = iterations
        for (i in 0..n - 1) {
            bh.consume(stringSwitch(localStrings[i]))
        }
    }
}

