import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestSuite {
    GameManager gameManager;
    WeaponFactory weaponFactory;
    ArmorFactory armorFactory;
    EnemyFactory enemyFactory;
    EnemyAttackGenerator enemyAttackGenerator;
    Dice dice;
    Character player;

    @Before
    public void setUp() {
        gameManager = new GameManager().getGameManager();
        weaponFactory = new WeaponFactory();
        armorFactory = new ArmorFactory();
        enemyFactory = new EnemyFactory();
        enemyAttackGenerator = new EnemyAttackGenerator();
        dice = new Dice();

        // create a test player
        String name = "player1";
        String race = "human";
        String role = "knight";

        player = new Character();

        Game.setPlayer(player);
        CharacterInfo playerInfo = player.getCharacterInfo();

        playerInfo.setName(name);
        playerInfo.setRace(race);
        playerInfo.setRole(role);

        Game.buildStarterCharacter();
    }

    @Test
    public void testDisplayMethods() {
        Game.delayExecutionUntilEnterIsPressed();

        Game.introMessage();
        Game.displayCharacterInfo();
        Game.displayCharacterStats();
        Game.displayInventory();
    }

    @Test
    public void testWeaponFactoryAndWeapons() {
        Sword swordTest = weaponFactory.makeSword();
        swordTest.setName("Apothican Sword");
        swordTest.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

        Staff staffTest = weaponFactory.makeStaff();
        staffTest.setName("Staff of Ice");
        staffTest.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);


        Bow bowTest = weaponFactory.makeBow();
        bowTest.setName("Wrath of the Ancients");
        bowTest.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

        assertEquals(Sword.class, swordTest.getClass());
        assertEquals(Staff.class, staffTest.getClass());
        assertEquals(Bow.class, bowTest.getClass());

        assertEquals(swordTest.getName(), "Apothican Sword");
        assertEquals(staffTest.getName(), "Staff of Ice");
        assertEquals(bowTest.getName(), "Wrath of the Ancients");

        assertEquals(swordTest.getBaseAttack(), WeaponConstants.SWORD_BASE_ATTACK, 0.0);
        assertEquals(swordTest.getAdditionalAttack(), WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK, 0.0);
        assertEquals(14.0, swordTest.getAttack(), 0.0);

        assertEquals(staffTest.getBaseAttack(), WeaponConstants.STAFF_BASE_ATTACK, 0.0);
        assertEquals(staffTest.getAdditionalAttack(), WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK, 0.0);
        assertEquals(15.0, staffTest.getAttack(), 0.0);

        assertEquals(bowTest.getBaseAttack(), WeaponConstants.BOW_BASE_ATTACK, 0.0);
        assertEquals(bowTest.getAdditionalAttack(), WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK, 0.0);
        assertEquals(13.0, bowTest.getAttack(), 0.0);
    }

    @Test
    public void testAlt() {
        Alt altTest = new Alt();
        altTest.setName("shout");
        altTest.setAdditionalAttack(WeaponConstants.ALT_ADDITIONAL_ATTACK);

        // test alt base attack
        assertEquals(WeaponConstants.ALT_BASE_ATTACK, altTest.getBaseAttack(), 0.0);

        // test alt additional attack
        assertEquals(WeaponConstants.ALT_ADDITIONAL_ATTACK, altTest.getAdditionalAttack(), 0.0);

        // test alt attack
        assertEquals(6.0, altTest.getAttack(), 0.0);

        // test alt cooldown
        assertEquals(WeaponConstants.ALT_COOLDOWN, altTest.getCooldown());

        for (int i = 0; i < WeaponConstants.ALT_COOLDOWN; i++) {
            altTest.decreaseCooldown();
        }

        assertEquals(0, altTest.getCooldown());
    }

    @Test
    public void testGameManager() {
        gameManager.alert(player, "player init");

        gameManager.alert(player, "init health");

//        gameManager.alert(player, "enter dungeon");
    }

    @Test
    public void testCharacterStats() {
        CharacterStats characterStats = player.getCharacterStats();

        characterStats.heal();
        characterStats.takeDamage(10);
        characterStats.takeDamage(1000);
        characterStats.heal();

        characterStats.addXp(10000);

        Game.compoundLevels();

        characterStats.increaseAttack();
        characterStats.increaseDefense();
        characterStats.increaseStamina();
        characterStats.increaseSpeed();

        characterStats.consumeStatPoint();
        characterStats.consumeStatPoint();
        characterStats.consumeStatPoint();
        characterStats.consumeStatPoint();

        assertEquals(100, player.getCharacterStats().getLevel());
        assertEquals(3, player.getCharacterStats().getAttack());
        assertEquals(3, player.getCharacterStats().getDefense());
        assertEquals(3, player.getCharacterStats().getStamina());
        assertEquals(2, player.getCharacterStats().getSpeed());
    }


    @Test
    public void testCharacterInventory() {
        CharacterInventory characterInventory = player.getCharacterInventory();

        characterInventory.addCoin(1000);
        assertEquals(1100, characterInventory.getCoin());

        characterInventory.spendCoin(1000);
        assertEquals(100, characterInventory.getCoin());


        assertEquals(0, characterInventory.getAntidotes());
        characterInventory.addAntidotesPotion();
        assertEquals(1, characterInventory.getAntidotes());
        characterInventory.useAntidotePotion();
        assertEquals(0, characterInventory.getAntidotes());

        assertEquals(0, characterInventory.checkTome(1));
        characterInventory.addTome(1);
        assertEquals(1, characterInventory.checkTome(1));
        characterInventory.useTome(1);
        assertEquals(0, characterInventory.checkTome(1));

        assertEquals(0, characterInventory.checkTome(2));
        characterInventory.addTome(2);
        assertEquals(1, characterInventory.checkTome(2));
        characterInventory.useTome(2);
        assertEquals(0, characterInventory.checkTome(2));

        assertEquals(0, characterInventory.checkTome(3));
        characterInventory.addTome(3);
        assertEquals(1, characterInventory.checkTome(3));
        characterInventory.useTome(3);
        assertEquals(0, characterInventory.checkTome(3));

        // error case
        assertEquals(0, characterInventory.checkTome(4));
    }

    @Test
    public void testEnemy() {
        Enemy testMinorEnemy = enemyFactory.makeMinorEnemy();
        testMinorEnemy.setName("enemy1");
        testMinorEnemy.setAttackName("fist");
        testMinorEnemy.setAttack(1.0);
        testMinorEnemy.setHealth(100.0);

        Enemy testMajorEnemy = enemyFactory.makeMajorEnemy();
        testMajorEnemy.setName("enemy2");
        testMajorEnemy.setAttackName("fist");
        testMajorEnemy.setAttack(1.0);
        testMajorEnemy.setHealth(100.0);

        Enemy testBossEnemy = enemyFactory.makeBossEnemy();
        testBossEnemy.setName("enemy3");
        testBossEnemy.setAttackName("fist");
        testBossEnemy.setAttack(1.0);
        testBossEnemy.setHealth(100.0);

        assertEquals(100.0, testMinorEnemy.getHealth(), 0.0);
        assertEquals("enemy1", testMinorEnemy.getName());
        assertEquals("fist", testMinorEnemy.getAttackName());
        assertEquals(1.0, testMinorEnemy.getAttack(), 0.0);
        assertEquals(100.0, testMinorEnemy.getHealth(), 0.0);
        testMinorEnemy.takeDamage(50.0);
        testMinorEnemy.takeDamage(50.0);
        assertEquals(0.0, testMinorEnemy.getHealth(), 0.0);

        assertEquals(100.0, testMajorEnemy.getHealth(), 0.0);
        assertEquals("enemy2", testMajorEnemy.getName());
        assertEquals("fist", testMajorEnemy.getAttackName());
        assertEquals(1.0, testMajorEnemy.getAttack(), 0.0);
        assertEquals(100.0, testMajorEnemy.getHealth(), 0.0);
        testMajorEnemy.takeDamage(100.0);
        assertEquals(0.0, testMajorEnemy.getHealth(), 0.0);

        assertEquals(100.0, testBossEnemy.getHealth(), 0.0);
        assertEquals("enemy3", testBossEnemy.getName());
        assertEquals("fist", testBossEnemy.getAttackName());
        assertEquals(1.0, testBossEnemy.getAttack(), 0.0);
        assertEquals(100.0, testBossEnemy.getHealth(), 0.0);
        testBossEnemy.takeDamage(100.0);
        assertEquals(0.0, testBossEnemy.getHealth(), 0.0);
    }

    // not sure how to test this one as it generates random results
    @Test
    public void testEnemyAttackGenerator() {
        int RESULTS = 300;

        int times = ((int) (((double )RESULTS) / 3.0));

        ArrayList<String> results = new ArrayList<>();

        for (int i = 0; i < times; i++) {
            results.add(enemyAttackGenerator.getRandomMinorEnemyAttack());
        }

        for (int i = 0; i < times; i++) {
            results.add(enemyAttackGenerator.getRandomMajorEnemyAttack());
        }

        for (int i = 0; i < times; i++) {
            results.add(enemyAttackGenerator.getRandomBossEnemyAttack());
        }

        for (String attackName : results) {
            System.out.println(attackName);
        }
    }

    // same as above
    @Test
    public void testRNG() {
        int RESULTS = 10000;

        double CHANCE = 0.50;

        int timesTrue = 0;
        int timesFalse = 0;

        double pTrue;
        double pFalse;

        for (int i = 0; i < RESULTS; i++) {
            boolean roll = dice.roll(CHANCE);

            if (roll) {
                timesTrue++;
            } else {
                timesFalse++;
            }
        }

        pTrue = (timesTrue / ((double) RESULTS)) * 100.0;
        pFalse = (timesFalse / ((double) RESULTS)) * 100.0;

        assertEquals(50.0, pTrue, 2.0);
        assertEquals(50.0, pFalse, 2.0);

        // was curious
        System.out.println();
        System.out.println(pTrue + "% true; " + pFalse + "% false");
    }

    @Test
    public void testDungeonManager() {
        DungeonManager.displaySkull();
        DungeonManager.generateLayer();
        DungeonManager.displayDungeonTitle();
        DungeonManager.displayDungeonInfo();
        DungeonManager.initBattle();
        DungeonManager.displayHealthStats();
//        DungeonManager.battleLoop();
        DungeonManager.openTreasure();

        assertEquals(1, DungeonManager.getDepth());
    }

    @Test
    public void testArmorClasses() {
        HeadArmor testHeadArmor = armorFactory.makeHeadArmor();
        testHeadArmor.setName("test helmet");
        testHeadArmor.setResistance(1.0);

        ArmArmor testArmArmor = armorFactory.makeArmsArmor();
        testArmArmor.setName("test arms");
        testArmArmor.setResistance(1.0);

        ChestArmor testChestArmor = armorFactory.makeChestArmor();
        testChestArmor.setName("test chestplate");
        testChestArmor.setResistance(1.0);

        LegArmor testLegArmor = armorFactory.makeLegArmor();
        testLegArmor.setName("test legs");
        testLegArmor.setResistance(1.0);

        BootArmor testBootArmor = armorFactory.makeBootArmor();
        testBootArmor.setName("test boots");
        testBootArmor.setResistance(1.0);

        Assert.assertNotNull(testHeadArmor);
        assertEquals("test helmet", testHeadArmor.getName());
        assertEquals(1.0, testHeadArmor.getResistance(), 0.0);

        Assert.assertNotNull(testArmArmor);
        assertEquals("test arms", testArmArmor.getName());
        assertEquals(1.0, testArmArmor.getResistance(), 0.0);

        Assert.assertNotNull(testChestArmor);
        assertEquals("test chestplate", testChestArmor.getName());
        assertEquals(1.0, testChestArmor.getResistance(), 0.0);

        Assert.assertNotNull(testLegArmor);
        assertEquals("test legs", testLegArmor.getName());
        assertEquals(1.0, testLegArmor.getResistance(), 0.0);

        Assert.assertNotNull(testBootArmor);
        assertEquals("test boots", testBootArmor.getName());
        assertEquals(1.0, testBootArmor.getResistance(), 0.0);
    }
}
