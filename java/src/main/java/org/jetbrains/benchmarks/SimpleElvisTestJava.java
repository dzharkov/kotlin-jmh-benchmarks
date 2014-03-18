package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SimpleElvisTestJava {

    @GenerateMicroBenchmark
    public void solve(BlackHole bh)  {
        int q = ElvisTestData.count;
        while (q-->0) {
            ElvisTestData instance = ElvisTestData.nextInstance();
            ElvisTestData instance1 = ElvisTestData.nextInstance();
            bh.consume((instance != null ? instance.value : 1) * ((instance1 != null ? instance1.value : 5)));
        }
    }
}
