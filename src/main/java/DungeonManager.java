import java.util.Random;

public class DungeonManager {
    private static String active_event = "coin bonus";
    private static boolean battle_active = false;
    private static boolean player_moves_first;
    private static int tick = 1;
    private static int depth = 1;
    private static int turn = 1;
    private static Enemy enemy = null;

    public void tick() {
        if (tick == 1) {
            tick = 2;
            active_event = "bonus xp";
        } else if (tick == 2) {
            tick = 3;
            active_event = "increased miss";
        } else if (tick == 3) {
            tick = 4;
            active_event = "increased critical chance";
        } else if (tick == 4) {
            tick = 1;
            active_event = "bonus coins";
        }
    }

    public void descend() {
        depth++;

        if ((depth % 3) == 0) {
            tick();
        }
    }

    public static void displayDanger() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/,,**/((((///((((/&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,,,,,*////(%%%#%%%##((##%#(/(/%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.,,,,***//(###%%%%%%%&&&&&%%##(((/&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,.,,,,*,*(((###%%%%%%%&&&@&&%#*((**(%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@..***,..,*/(#&%#(((#((#%&&%&%%##&%#,(/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(....**,,,,,**/**/#/(%%#(/(#&&%#(#(/##/&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*... */%*.    .,,*,**(((*,,,.,,,,(#/%(/(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&,...#**,       .,./%&&*,....,*,..,#%##(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ..,&(#*,...   ..*  %%&/  ..... .*(##(#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&. ,%#%/*.    ..,.   ((###(  . .*&#**/#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  .,/%&&&%/*,,#      .%(#(/((/##%&&##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%. .,***/*,  .(.  .   &%(#%(((#/%@&/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@, .///*/*/..**  /,.@&%(%(/, .,&*#(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@... &* .,,,/(*,#(#&@#&%( .,(#(/#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.,(@@(.,(((*#*#&%&&#&%@@.&#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@... @/. /.,/*(#%,*.#(@@*/&%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ ,*  ,..(###(%/&@@%@#&*%@%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(.,,,*. / #(%%.##.&(.#%%@##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.,** .,.,(*((,#/#&#&&%%%(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(,.*,. ,#(##&@&&&&&%%%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%..,*(((/%&&@%(%%&#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@& .,*/((%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public static void displayDungeonTitle() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ --                                           Dungeon                                          -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public static void displayDungeonInfo() {
        System.out.println(ConsoleColors.RED + depth + ConsoleColors.DEFAULT + " layers below the surface");
        System.out.println("active event: " + ConsoleColors.YELLOW + active_event + ConsoleColors.DEFAULT);
        System.out.println();
        System.out.println(ConsoleColors.RED + enemy.getName() + " has appeared" + ConsoleColors.DEFAULT);
    }

    public static void generateLayer() {
        EnemyFactory enemyFactory = new EnemyFactory();
        EnemyAttackGenerator enemyAttackGenerator = new EnemyAttackGenerator();

        // 1 for minor, 2 for major, 3 for boss
        int enemyDensity = 1;

        if ((depth % 10) == 5) {
            enemyDensity = 2;
        } else if ((depth % 10) == 0) {
            enemyDensity = 3;
        }

        if (enemyDensity == 1) {
            enemy = enemyFactory.makeMinorEnemy();

            double health = EnemyConstants.MINOR_ENEMY_BASE_HEALTH + (depth * EnemyConstants.DEPTH_HEALTH_BONUS);

            enemy.setHealth(health);


            enemy.setAttackname(enemyAttackGenerator.getRandomMinorEnemyAttack());

            double damage = EnemyConstants.MINOR_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        } else if (enemyDensity == 2) {
            enemy = enemyFactory.makeMajorEnemy();

            double health = EnemyConstants.MAJOR_ENEMY_BASE_HEALTH + (depth * EnemyConstants.DEPTH_HEALTH_BONUS);

            enemy.setHealth(health);


            enemy.setAttackname(enemyAttackGenerator.getRandomMajorEnemyAttack());

            double damage = EnemyConstants.MAJOR_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        } else if (enemyDensity == 3) {
            enemy = enemyFactory.makeBossEnemy();

            double health = EnemyConstants.BOSS_ENEMY_BASE_HEALTH + (depth * EnemyConstants.DEPTH_HEALTH_BONUS);

            enemy.setHealth(health);


            enemy.setAttackname(enemyAttackGenerator.getRandomBossEnemyAttack());

            double damage = EnemyConstants.BOSS_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        }
    }

    public static void initBattle() {
        System.out.println();
        System.out.println();
        System.out.println();

        Random random = new Random();

        int playerSpeed = Game.getPlayer().getCharacterStats().getSpeed();

        int enemySpeed = random.nextInt(playerSpeed + 1);

        if (playerSpeed > enemySpeed) {
            player_moves_first = true;
            System.out.println("you have a higher speed. your move first.");
        } else if (playerSpeed < enemySpeed) {
            player_moves_first = false;
            System.out.println(enemy.getName() + " has a higher speed. " + enemy.getName() + " will move first.");
        }
    }

    public static void promptPlayerMove() {

    }

    public static void computerMove() {

    }

    public static void battleLoop() {
        System.out.println();

        while (battle_active) {
            if ((player_moves_first) && (turn == 1)) {
                // player moves
                promptPlayerMove();
            } else if ((!player_moves_first) && (turn == 1)) {
                // computer turn
                computerMove();
            } else if ((!player_moves_first) && (turn == 2)) {
                promptPlayerMove();
            } else if ((player_moves_first) && (turn == 2)) {
                computerMove();
            }

            // check health stats
            if (Game.getPlayer().getCharacterStats().getHealth() < 1) {
                // player lost
            } else if (enemy.getHealth() < 1) {
                // player wins
            }

            if (turn == 1) {
                turn = 2;
            } else if (turn == 2) {
                turn = 1;
            }
        }

        // end the battle
    }

    public static void dungeonLoop() {
//        displayDanger();
//        displayDungeonTitle();

        generateLayer();
        displayDungeonInfo();
        initBattle();
        battleLoop();
    }
}
