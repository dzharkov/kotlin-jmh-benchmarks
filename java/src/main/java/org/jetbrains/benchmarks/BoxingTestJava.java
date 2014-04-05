package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BoxingTestJava {
    public static final int SIZE = 1000000;
    private int[] array = new int[SIZE];

    @Setup
    public void init() {
        Random random = new Random(123);
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt();
        }
    }

    @GenerateMicroBenchmark
    public void testWithBoxingMethod(BlackHole bh) {
        for (int i = 0; i < SIZE; i++) {
            bh.consume(Integer.valueOf(array[i]).intValue());
        }
    }

    @GenerateMicroBenchmark
    public void testWithoutBoxingMethod(BlackHole bh) {
        for (int i = 0; i < SIZE; i++) {
            bh.consume(array[i]);
        }
    }
}
