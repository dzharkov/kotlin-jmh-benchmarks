package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.SampleTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SimpleElvisTestJava {

    @GenerateMicroBenchmark
    public void solve(BlackHole bh)  {
        int q = ElvisTestData.count;
        ElvisTestData.init();
        while (q-->0) {
            ElvisTestData instance = ElvisTestData.nextInstance();
            ElvisTestData instance1 = ElvisTestData.nextInstance();
            bh.consume((instance != null ? instance.value : ElvisTestData.next()) * ((instance1 != null ? instance1.value : ElvisTestData.next())));
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + SimpleElvisTestJava.class.getSimpleName() + ".*")
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
