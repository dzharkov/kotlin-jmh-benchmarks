package org.jetbrains.benchmarks.stars;

import java.util.Random;

public class StarsData {
    public static Random random;

    public static void init() {
        random = new Random(123);
    }

    static int currentX = -1000000000;
    static int step = 0;

    public static int getX() {
        if (step % 2 == 0) {
            currentX += 10 * random.nextInt();
        } else {
            currentX -= 5 * random.nextInt();
        }

        step++;

        return currentX;
    }
}
