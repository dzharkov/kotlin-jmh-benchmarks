package org.jetbrains.benchmarks.switchopcode;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SwitchTestJava {
    @Param({"100", "1000", "1000000"})
    int iterations;

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int sparseSwitch(int u) {
        switch (u) {
            case 1:
                return 1;
            case 100500:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 1;
            case 8:
                return 9;
            case 9:
                return 1;
            case 10:
                return 2;
            case 11:
                return 3;
            case 12:
                return 4;
            case 13:
                return 4;
            case 14:
                return 4;
            case 15:
                return 435;
            case 16:
                return 31;
            case 17:
                return 1;
            case 18:
                return 1;
            case 19:
                return 1;
            case 20:
                return 1;
            default:
                return 5;
        }
    }

    @Benchmark
    public void testSparseSwitch(Blackhole bh) {
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(sparseSwitch(i));
        }
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int denseSwitch(int u) {
        switch (u) {
            case 1:
                return 1;
            case (-1):
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 1;
            case 8:
                return 9;
            case 9:
                return 1;
            case 10:
                return 2;
            case 11:
                return 3;
            case 12:
                return 4;
            case 13:
                return 4;
            case 14:
                return 4;
            case 15:
                return 435;
            case 16:
                return 31;
            case 17:
                return 1;
            case 18:
                return 1;
            case 19:
                return 1;
            case 20:
                return 1;
            default:
                return 5;
        }
    }

    @Benchmark
    public void testDenseSwitch(Blackhole bh) {
        int n = iterations;
        for (int i = 0; i < n; i++) {
            bh.consume(denseSwitch(i));
        }
    }
}
