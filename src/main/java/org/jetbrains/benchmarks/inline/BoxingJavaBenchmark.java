package org.jetbrains.benchmarks.inline;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BoxingJavaBenchmark {
    public int value = 12345678;

    @Benchmark
    public int benchmarkWithBoxingMethod() {
        return Integer.valueOf(value *= 3).intValue();
    }

    @Benchmark
    public int benchmarkWithoutBoxingMethod() {
        return value *= 3;
    }
}
