package game;

import java.util.Random;

public abstract class RandomGenerator {
    protected static int generateRandomNum(int bound) {
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}