package org.jetbrains.benchmarks.ms;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MergeSortJavaBenchmark {
    @Param({"100", "1000", "1000000"})
    int size;

    private int[] data;
    private int[] helper;

    @Setup
    public void init() {
        helper = new int[size];
        data = new int[size];
        Random random = new Random(123);
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt();
        }
    }

    private void mergeSort(int[] a, int from, int to) {
        if (from >= to) {
            return;
        }

        int m = (from + to) / 2;

        mergeSort(a, from, m);
        mergeSort(a, m + 1, to);

        int k = 0;
        int i = from;
        int j = m + 1;

        while (i <= m && j <= to) {
            if (a[i] < a[j]) {
                helper[k++] = a[i++];
            } else {
                helper[k++] = a[j++];
            }
        }

        while (i <= m) {
            helper[k++] = a[i++];
        }

        while (j <= to) {
            helper[k++] = a[j++];
        }

        System.arraycopy(helper, 0, a, from, k);
    }

    @Benchmark
    public void benchmarkMethod(Blackhole bh) {
        int[] a = data;
        int size = this.size;
        mergeSort(a, 0, size - 1);

        bh.consume(a);
    }
}
