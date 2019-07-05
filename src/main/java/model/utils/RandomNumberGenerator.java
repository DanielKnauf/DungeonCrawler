package model.utils;

import java.util.Random;

public class RandomNumberGenerator {

    private final Random randomize = new Random();

    public int getRandomInteger(int bound) {
        return bound > 0
                ? randomize.nextInt(bound)
                : -1;
    }
}
