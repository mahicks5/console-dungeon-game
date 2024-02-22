import java.util.Random;

public class EnemyAttackGenerator {
    private final String[] minorEnemyAttack = {
        "sword",
        "bow",
        "bite",
        "fist"
    };
    private final String[] majorEnemyAttack = {
        "claymore",
        "elemental bow",
        "triple bite",
        "blade claws",
        "awful scream"
    };
    private final String[] bossEnemyAttack = {
        "ground stomp",
        "rock throw",
        "mega smack",
        "firebreath",
        "ice breath",
        "kamehameha",
        "ear-piercing scream",
        "fireball",
        "bone hammer",
        "thousand-uppercut",
        "deathcall"
    };

    /**
     * Returns a new minor enemy attack name. */
    public String getRandomMinorEnemyAttack() {
        Random random = new Random();

        return minorEnemyAttack[random.nextInt(minorEnemyAttack.length)];
    }

    /**
     * Returns a new major enemy attack name. */
    public String getRandomMajorEnemyAttack() {
        Random random = new Random();

        return majorEnemyAttack[random.nextInt(majorEnemyAttack.length)];
    }

    /**
     * Returns a new boss enemy attack name. */
    public String getRandomBossEnemyAttack() {
        Random random = new Random();

        return bossEnemyAttack[random.nextInt(bossEnemyAttack.length)];
    }
}
