import java.util.Random;

public class Dice {
    /**
     * Simulates a roll of a die with the given chance.
     * @param chance the percentage chance */
    public boolean roll(double chance) {
        Random random = new Random();

        int number = random.nextInt(102) - 1;

        int bound = (int) (chance * 100);

        return number < bound;
    }
}
