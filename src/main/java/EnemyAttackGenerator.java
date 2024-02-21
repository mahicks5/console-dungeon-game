import java.util.Random;

public class EnemyAttackGenerator {
    private String[] minorEnemyAttack = {
            "sword",
            "bow",
            "bite",
            "fist"
    };
    private String[] majorEnemyAttack = {
            "claymore",
            "elemental bow",
            "triple bite",
            "blade claws",
            "awful scream"
    };
    private String[] bossEnemyAttack = {
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

    public String getRandomMinorEnemyAttack() {
        Random random = new Random();

        return minorEnemyAttack[random.nextInt(minorEnemyAttack.length)];
    }

    public String getRandomMajorEnemyAttack() {
        Random random = new Random();

        return majorEnemyAttack[random.nextInt(majorEnemyAttack.length)];
    }

    public String getRandomBossEnemyAttack() {
        Random random = new Random();

        return bossEnemyAttack[random.nextInt(bossEnemyAttack.length)];
    }
}
