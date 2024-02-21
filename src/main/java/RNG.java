import java.util.Random;

public class RNG {
    public boolean roll(double chance) {
        Random random = new Random();

        int number = random.nextInt(102) - 1;

        int bound = (int) (chance * 100);

        return number < bound;
    }
}
