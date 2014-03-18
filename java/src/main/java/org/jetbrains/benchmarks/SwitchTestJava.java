package org.jetbrains.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Denis Zharkov
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SwitchTestJava {
    public static final int ITERATIONS = 100000000;

    int g(int u) {
        if (u > 100) {
            return 0;
        }

        return u+10;
    }

    int f1(int u) {
        int t;
        switch (u) {
            case (1):
                t = 1;
                break;
            case (100500):
                t = 2;
                break;
            case (2):
                t = 3;
                break;
            case (3):
                t = 4;
                break;
            case (4):
                t = 5;
                break;
            case (5):
                t = 6;
                break;
            case (6):
                t = 7;
                break;
            case (7):
                t = 1;
                break;
            case (8):
                t = 9;
                break;
            case (9):
                t = 1;
                break;
            case (10):
                t = 2;
                break;
            case (11):
                t = 3;
                break;
            case (12):
                t = 4;
                break;
            case (13):
                t = 4;
                break;
            case (14):
                t = 4;
                break;
            case (15):
                t = 435;
                break;
            case (16):
                t =31;
                break;
            case (17):
                t = 1;
                break;
            case (18):
                t = 1;
                break;
            case (19):
                t = 1;
                break;
            case (20):
                t = 1;
                break;
            default:
                t = 5;
        }

        return g(t);
    }

    @GenerateMicroBenchmark
    public int testSwitch1() {
        int sum = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            sum += f1(i);
        }
        return sum;
    }

    int f2(int u) {
        int t;
        switch (u) {
            case (1):
                t = 1;
                break;
            case (-1):
                t = 2;
                break;
            case (2):
                t = 3;
                break;
            case (3):
                t = 4;
                break;
            case (4):
                t = 5;
                break;
            case (5):
                t = 6;
                break;
            case (6):
                t = 7;
                break;
            case (7):
                t = 1;
                break;
            case (8):
                t = 9;
                break;
            case (9):
                t = 1;
                break;
            case (10):
                t = 2;
                break;
            case (11):
                t = 3;
                break;
            case (12):
                t = 4;
                break;
            case (13):
                t = 4;
                break;
            case (14):
                t = 4;
                break;
            case (15):
                t = 435;
                break;
            case (16):
                t =31;
                break;
            case (17):
                t = 1;
                break;
            case (18):
                t = 1;
                break;
            case (19):
                t = 1;
                break;
            case (20):
                t = 1;
                break;
            default:
                t = 5;
        }

        return g(t);
    }

    @GenerateMicroBenchmark
    public int testSwitch2() {
        int sum = 0;
        for (int i = 0; i < ITERATIONS; i++) {
            sum += f2(i);
        }
        return sum;
    }
}
