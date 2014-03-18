package org.jetbrains.benchmarks;

/**
 * @author Denis Zharkov
 */
public class StarsData {
    public static int getN() {
        step = 500000;
        return 100000;
    }

    static int currentX = -1000000000;
    static int step = 0;

    public static int getX() {
        if (step %2 == 0) {
            currentX += 10 * step;
        } else {
            currentX -= 5 * step;
        }

        step++;

        return currentX;
    }
}
