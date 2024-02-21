import java.util.Random;

public class EnemyNameGenerator {
    private final String[] minorEnemyNames = {
            "skeleton",
            "spider",
            "zombie",
            "rat"
    };
    private final String[] majorEnemyNames = {
            "skeleton warrior",
            "zombie prince",
            "16-legged spider",
            "mutant rat",
            "mad geist"
    };
    private final String[] bossEnemyNames = {
            "deepclaw, the blackened fang",
            "goryo ratblood",
            "toothdagger, demented",
            "boris the heartless",
            "aggar manyarms",
            "skor, prince of bones",
            "gothar, ruler of the undead",
            "fuliee the threatening"
    };

    public String getRandomMinorEnemyName() {
        Random random = new Random();

        return minorEnemyNames[random.nextInt(minorEnemyNames.length)];
    }

    public String getRandomMajorEnemyName() {
        Random random = new Random();

        return majorEnemyNames[random.nextInt(majorEnemyNames.length)];
    }

    public String getRandomBossEnemyName() {
        Random random = new Random();

        return bossEnemyNames[random.nextInt(bossEnemyNames.length)];
    }
}
