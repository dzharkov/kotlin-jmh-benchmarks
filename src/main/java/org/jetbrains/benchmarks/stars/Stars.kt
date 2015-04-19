package org.jetbrains.benchmarks.stars

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import java.util.Comparator
import java.util.concurrent.TimeUnit

[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime)]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class StarsKotlin() {
    Param("100", "1000", "100000")
    var size: Int = 1

    Setup fun init() {
        StarsData.init()
    }

    object BaseNodeInfo : NodeInfo() {
        override fun height(node: KotlinAVLTree.Node<*, *>?) = node?.height ?: 0
        override fun size(node: KotlinAVLTree.Node<*, *>?) = node?.size ?: 0
    }

    Benchmark
    public fun baseBenchmark(bh: Blackhole) {
        solve(bh, BaseNodeInfo, size)
    }

    object FasterNodeInfo : NodeInfo() {
        override fun height(node : KotlinAVLTree.Node<*,*>?) = if (node != null) node.height else 0
        override fun size(node : KotlinAVLTree.Node<*,*>?) = if (node != null) node.size else 0
    }

    Benchmark
    public fun baseBenchmarkFaster(bh: Blackhole) {
        solve(bh, FasterNodeInfo, size)
    }
}
