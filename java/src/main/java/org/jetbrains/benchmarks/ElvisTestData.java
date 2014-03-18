package org.jetbrains.benchmarks;

/**
 * @author Denis Zharkov
 */
public class ElvisTestData {
    public int value;

    public ElvisTestData(int value) {
        this.value = value;
    }

    public static int count = 1000000;
    public static int step = 0;


    public static ElvisTestData nextInstance() {
        step++;
        if (step % 100 < 50) {
            return null;
        } else {
            return new ElvisTestData((int)Math.exp(step));
        }
    }
}
