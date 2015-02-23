package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BoxingTestJava {
    public int value = 12345678;

    @Benchmark
    public int testWithBoxingMethod() {
        return Integer.valueOf(value *= 3).intValue();
    }

    @Benchmark
    public int testWithoutBoxingMethod() {
        return value *= 3;
    }
}
