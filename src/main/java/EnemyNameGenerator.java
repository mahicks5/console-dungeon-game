import java.util.Random;

public class EnemyNameGenerator {
    private String[] minorEnemyNames = {
            "skeleton",
            "spider",
            "zombie",
            "rat"
    };
    private String[] majorEnemyNames = {
            "skeleton warrior",
            "zombie prince",
            "16-legged spider",
            "mutant rat",
            "mad geist"
    };
    private String[] bossEnemyNames = {
            "deepclaw, the blackened fang",
            "goryo ratblood",
            "toothdagger, demented",
            "boris the heartless",
            "aggar manyarms",
            "skor, prince of bones",
            "gothar, ruler of the undead",
            "fuliee the threatning"
    };

    public String getRandomMinorEnemyName() {
        Random random = new Random();

        return minorEnemyNames[random.nextInt(minorEnemyNames.length + 1)];
    }

    public String getRandomMajorEnemyName() {
        Random random = new Random();

        return majorEnemyNames[random.nextInt(majorEnemyNames.length + 1)];
    }

    public String getRandomBossEnemyName() {
        Random random = new Random();

        return bossEnemyNames[random.nextInt(bossEnemyNames.length + 1)];
    }
}
