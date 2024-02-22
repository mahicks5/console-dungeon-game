/**
 * EnemyFactory.java used to create enemies. someone should stop them.
 * */

public class EnemyFactory {
    /**
     * Returns a new minor enemy.
     * @return new minor enemy */
    public Enemy makeMinorEnemy() {
        Enemy minorEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        minorEnemy.setName(nameGenerator.getRandomMinorEnemyName());

        return  minorEnemy;
    }

    /**
     * Returns a new major enemy.
     * @return new major enemy */
    public Enemy makeMajorEnemy() {
        Enemy majorEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        majorEnemy.setName(nameGenerator.getRandomMajorEnemyName());

        return  majorEnemy;
    }

    /**
     * Returns a new boss enemy.
     * @return new boss enemy */
    public Enemy makeBossEnemy() {
        Enemy bossEnemy = new Enemy();

        EnemyNameGenerator nameGenerator = new EnemyNameGenerator();

        bossEnemy.setName(nameGenerator.getRandomBossEnemyName());

        return  bossEnemy;
    }
}
