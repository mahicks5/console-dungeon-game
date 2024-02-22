/**
 * Dice.java
 * Used for seeing if someone gets lucky.
 * This uses the singleton pattern for secure randomness. */

import java.security.SecureRandom;

public final class Dice {
    private static volatile Dice instance;
    private final SecureRandom secureRandom;

    private Dice() {
        secureRandom = new SecureRandom();
    }

    /**
     * Gets the dice class.
     * @return the dice class instance */
    public static Dice getDice() {
        if (instance == null) {
            instance = new Dice();
        }

        return instance;
    }

    /**
     * Simulates a roll of a die with the given chance.
     * @param chance the percentage chance */
    public boolean roll(double chance) {
        int number = secureRandom.nextInt(102) - 1;

        int bound = (int) (chance * 100);

        return number < bound;
    }
}
