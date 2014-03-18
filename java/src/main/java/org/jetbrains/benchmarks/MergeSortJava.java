package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MergeSortJava {
    public static final int SIZE = 1000000;
    private int size = SIZE;
    private int[] helper = new int[size];

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

    @GenerateMicroBenchmark
    public void testMethod() {
        int size = this.size;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = size-i;
        }

        mergeSort(a, 0, size - 1);

        for (int i = 1; i < size; i++) {
            if (a[i] < a[i - 1]) {
                throw new RuntimeException("fsdfds");
            }
        }
    }
}
