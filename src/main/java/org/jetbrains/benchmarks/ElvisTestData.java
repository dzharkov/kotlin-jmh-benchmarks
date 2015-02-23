package org.jetbrains.benchmarks;

import java.util.Random;

public class ElvisTestData {
    public int value;

    public ElvisTestData(int value) {
        this.value = value;
    }

    public static int arraySize = 1000000;
    public static int count = 10000000;
    public static Random random;

    public static void init() {
        random = new Random(123);
    }

    public static ElvisTestData nextInstance() {
        if (random.nextInt() % 2 == 1) {
            return null;
        } else {
            return new ElvisTestData((int)Math.exp(random.nextDouble()));
        }
    }

    public static int next() {
        return random.nextInt();
    }
}
