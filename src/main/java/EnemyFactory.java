/**
 * EnemyFactory.java used to create enemies. someone should stop them
 * */

public class EnemyFactory {
    public Enemy makeMinorEnemy() {
        Enemy minorEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        minorEnemy.setName(nameGenerator.getRandomMinorEnemyName());

        return  minorEnemy;
    }

    public Enemy makeMajorEnemy() {
        Enemy majorEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        majorEnemy.setName(nameGenerator.getRandomMajorEnemyName());

        return  majorEnemy;
    }

    public Enemy makeBossEnemy() {
        Enemy bossEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        bossEnemy.setName(nameGenerator.getRandomBossEnemyName());

        return  bossEnemy;
    }
}
