import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DungeonManager {
    private static Enemy enemy = null;
    private static String active_event = "coin bonus";
    private static boolean battle_active = false;
    private static boolean player_moves_first;
    private static int tick = 1;
    private static int depth = 1;
    private static int turn = 1;

    public static void tick() {
        if (tick == 1) {
            tick = 2;
            active_event = Events.event2;
        } else if (tick == 2) {
            tick = 3;
            active_event = Events.event3;
        } else if (tick == 3) {
            tick = 4;
            active_event = Events.event4;
        } else if (tick == 4) {
            tick = 1;
            active_event = Events.event1;
        }
    }

    public static void descend() {
        depth++;

        if ((depth % 3) == 0) {
            tick();
        }
    }

    public static int getDepth() {
        return depth;
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

        delayExecutionUntilEnterIsPressed();
    }

    public static void displayDungeonTitle() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- dungeon                                                                                    -- ]");
    }

    public static void displayDungeonInfo() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
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

        battle_active = true;
    }

    public static void promptPlayerMove() {
        Scanner userInput = new Scanner(System.in);

        CharacterStats characterStats = Game.getPlayer().getCharacterStats();
        CharacterInventory characterInventory = Game.getPlayer().getCharacterInventory();

        int selection = 0; // default; do nothing with 0

        boolean altReady;
        boolean potions_enabled = false;
        boolean flee_enabled = false;

        altReady = characterInventory.getAlt().getCooldown() == 0;

        boolean invalidChoice = false;


        while (selection == 0) {
            if (!invalidChoice) {
                System.out.println("make a choice");
            } else {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println(
                    "1.) use " +
                    ConsoleColors.BLUE + characterInventory.getWeapon().getName() + ConsoleColors.DEFAULT
            );
            if (altReady) {
                System.out.println(
                        "2.) use " +
                                ConsoleColors.BLUE + characterInventory.getAlt().getName() + ConsoleColors.DEFAULT
                );
            }

            if (characterInventory.checkPotions() > 0) {
                potions_enabled = true;

                System.out.println("3.) health potion (" + characterInventory.checkPotions() + " remaining)");
            }

            if ((depth % 10) == 0) {
                flee_enabled = true;

                System.out.println("4.) flee");
            }

            int choice = userInput.nextInt();

            if ((choice == 2) && (!altReady)) {
                invalidChoice = true;
            } else if ((choice == 3) && (!potions_enabled)) {
                invalidChoice = true;
            } else if ((choice == 4) && (!flee_enabled)) {
                invalidChoice = true;
            } else {
                selection = choice;
            }
        }

        if (selection == 1) {
            // attack with main
            // begin critical and miss modifiers
            RNG rng = new RNG();

            double damage = Game.getPlayer().getCharacterInventory().getWeapon().getAttack();

            // chance to hit critical. includes event
            if (active_event.equals(Events.event4)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            }

            // chance to miss. includes event
            if (active_event.equals(Events.event3)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE * EventConstants.INCREASED_MISS_CHANCE);

                if (chance) {
                    System.out.println();
                    System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 0;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE);

                if (chance) {
                    System.out.println();
                    System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 0;
                }
            }

            if (damage > 0) {
                System.out.println(
                        "you hit " +
                                ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT +
                                " with " +
                                ConsoleColors.BLUE + Game.getPlayer().getCharacterInventory().getWeapon().getName() + ConsoleColors.DEFAULT
                );
                System.out.println();
                System.out.println(
                        "the " +
                                ConsoleColors.RED + enemy.getName() + ConsoleColors.BLUE +
                                " takes " + damage + ConsoleColors.DEFAULT +
                                " of damage"

                );
            }

            // end critical and miss modifiers
            enemy.takeDamage(damage);

            System.out.println();
            System.out.println();
        }

        if ((selection == 2) && (altReady)) {
            // attack with alt
            // begin critical and miss modifiers
            RNG rng = new RNG();

            double damage = Game.getPlayer().getCharacterInventory().getAlt().getAttack();

            // chance to hit critical. includes event
            if (active_event.equals(Events.event4)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            }

            // chance to miss. includes event
            if (active_event.equals(Events.event3)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE * EventConstants.INCREASED_MISS_CHANCE);

                if (chance) {
                    System.out.println();
                    System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 0;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE);

                if (chance) {
                    System.out.println();
                    System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 0;
                }
            }

            if (damage > 0) {
                System.out.println(
                        "you hit " +
                                ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT +
                                " with " +
                                ConsoleColors.BLUE + Game.getPlayer().getCharacterInventory().getAlt().getName() + ConsoleColors.DEFAULT
                );
                System.out.println();
                System.out.println(
                        "the " +
                                ConsoleColors.RED + enemy.getName() + ConsoleColors.BLUE +
                                " takes " + damage + ConsoleColors.DEFAULT +
                                " of damage"

                );
            }

            // end critical and miss modifiers
            enemy.takeDamage(damage);

            System.out.println();
            System.out.println();
        }

        if ((selection == 3) && (potions_enabled)) {
            characterInventory.useHealthPotion();
            characterStats.heal();
        }

        if ((selection == 4) && (flee_enabled)) {
            battle_active = false;
            turn = 1;

            Game.gameloop(new Scanner(System.in));
        }

        characterInventory.getAlt().decreaseCooldown();
    }

    public static void computerMove() {
        System.out.println(
                ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT + " is preparing to strike"
        );

        delayExecutionUntilEnterIsPressed();


        // begin critical and miss modifiers
        RNG rng = new RNG();

        double damage = enemy.getAttack();

        // chance to hit critical. includes event
        if (active_event.equals(Events.event4)) {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

            if (chance) {
                System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 2;
            }
        } else {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_CRIT_CHANCE);

            if (chance) {
                System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 2;
            }
        }

        // chance to miss. includes event
        if (active_event.equals(Events.event3)) {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE * EventConstants.INCREASED_MISS_CHANCE);

            if (chance) {
                System.out.println();
                System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 0;
            }
        } else {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_MISS_CHANCE);

            if (chance) {
                System.out.println();
                System.out.println(ConsoleColors.CYAN + "missed!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 0;
            }
        }

        if (damage > 0) {
            System.out.println(
                    ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT +
                            " hits you with " +
                            ConsoleColors.PURPLE + enemy.getAttackname() + ConsoleColors.DEFAULT
            );

            System.out.println();

            System.out.println(
                    "you take " + ConsoleColors.PURPLE + damage + ConsoleColors.DEFAULT +
                            " of damage"
            );
        }

        // end critical and miss modifiers

        Game.getPlayer().getCharacterStats().takeDamage(damage);

        System.out.println();
        System.out.println();
    }

    public static void displayHealthStats() {
        System.out.println(
                ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT +
                        " health: " +
                        ConsoleColors.RED + enemy.getHealth() + ConsoleColors.DEFAULT
        );

        System.out.println(
                ConsoleColors.GREEN + Game.getPlayer().getCharacterInfo().getName() + ConsoleColors.DEFAULT +
                        " health: " +
                        ConsoleColors.GREEN + Game.getPlayer().getCharacterStats().getHealth() + ConsoleColors.DEFAULT
        );
        System.out.println();
        System.out.println();
    }

    public static void rewardPlayer() {
        CharacterStats characterStats = Game.getPlayer().getCharacterStats();
        CharacterInventory characterInventory = Game.getPlayer().getCharacterInventory();

        int coin = DungeonConstants.DEFAULT_COIN_REWARD + (depth * DungeonConstants.DEPTH_COIN_BONUS);
        int xp = DungeonConstants.DEFAULT_XP_REWARD + (depth * DungeonConstants.DEPTH_XP_BONUS);

        if (active_event.equals(Events.event1)) {
            coin *= EventConstants.BONUS_COINS;
        }

        if (active_event.equals(Events.event2)) {
            xp *= EventConstants.BONUS_XP;
        }

        System.out.println();
        System.out.println(
                "you have been awarded " +
                        ConsoleColors.YELLOW + coin + ConsoleColors.DEFAULT +
                        " coin and " +
                        ConsoleColors.YELLOW + xp + ConsoleColors.DEFAULT +
                        " xp"
        );

        characterInventory.addCoin(coin);
        characterStats.addXp(xp);

        delayExecutionUntilEnterIsPressed();

        RNG rng = new RNG();

        boolean chance = rng.roll(DungeonConstants.DEFAULT_BONUS_CHEST_CHANCE);

        if (chance) {
            openTreasure();
        }

        descend();

        dungeonLoop();
    }

    public static void determineWinner() {
        System.out.println();

        if (Game.getPlayer().getCharacterStats().getHealth() > 0) {
            System.out.println(
                    ConsoleColors.GREEN + Game.getPlayer().getCharacterInfo().getName() + ConsoleColors.DEFAULT +
                            " has won");

            // reward player
            rewardPlayer();
        } else if (Game.getPlayer().getCharacterStats().getHealth() == 0) {
            System.out.println(
                    "you have met your fate. " +
                    ConsoleColors.RED + enemy.getName() + ConsoleColors.DEFAULT +
                    " has won"
            );

            int lostCoin = (depth * DungeonConstants.DEFAULT_COIN_LOSS_MULTIPLIER);

            System.out.println();
            System.out.println(
                    "you lost " +
                            ConsoleColors.YELLOW + lostCoin + ConsoleColors.DEFAULT +
                            " coin"
            );

            Game.gameloop(new Scanner(System.in));
        }
    }

    public static void openTreasure() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&%,*/(///******/**/////*/////**/(///**//***%%#*.,,%&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&@&,*/*////*//**///*////**/**/*/*///*///(/*/&&%(..,,,*&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&@@@**,,*,*/*////(/(/((//((/(//////(/(//(///(@&@%,,,,,.,*&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&%@*(////////////*/*,*,,*/////((/((((((/(((((@@@(*,,.,.,/&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&%%*#&./**********//@%@(*****,,,/////(/((((((@@@@*,,,,,.**&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&(*#(/,/**,*,***/*/####%,,**,*//,.,**////#&%/%@@/*,,.,.***&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&((#((%(/*//((##/##/##((#(####/##%( .****%/(/#%(,,*****//*&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&((,//*,,,,.,.*#(((/((//((/(((/##***(((###(((/#(**,**,,.,*&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&//*.*,*.**,,******((*((.*,,,,,,,,,,,,(#####(###**,,...,/*&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&((/,******,,******/*.((,/*. *,,*,,*,,,*,(/.((((*...,,,,/%&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&/((,****,********,./((.,**,***,,****,,****,(((*,.,.,.../&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&((.,**/**.*****/******/********/*,.*,**,**###*.,,.,,,./&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&((,*.,***************(**.,*,*,,********,,,##(*,.,,,.,.,%%%%%%%%%&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&((*,*/****/*,***/*.****/*******,*,*,/** ,*(((*,....,.*,###%%%%%%%%%%%&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&((((((/%#//*/////*****,,,**/*.,*******,/((#*,,.,,/*########%%%%%%%%&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&((((((((((/*.**,,,*******(//*(((**.*/*##########%%%%&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&(((((((/#((/.///*##(****##########%&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&(((#((#((*/####&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println();

        System.out.println("ooh! look! you found treasure!");

        delayExecutionUntilEnterIsPressed();

        CharacterInventory characterInventory = Game.getPlayer().getCharacterInventory();

        int coin = DungeonConstants.DEFAULT_CHEST_REWARD + (depth * DungeonConstants.DEPTH_COIN_BONUS);
        int potions = (new Random()).nextInt(DungeonConstants.MAX_CHEST_POTIONS + 1);

        if (active_event.equals(Events.event1)) {
            coin *= EventConstants.BONUS_COINS;
        }

        characterInventory.addCoin(coin);

        for (int i = 0; i < potions; i++){
            characterInventory.addHealthPotion();
        }

        System.out.println();
        System.out.println(
                "you found " +
                        ConsoleColors.YELLOW + coin + ConsoleColors.DEFAULT +
                        " coin and " +
                        ConsoleColors.YELLOW + potions + ConsoleColors.DEFAULT +
                        " potions"
        );

        delayExecutionUntilEnterIsPressed();
    }

    public static void delayExecutionUntilEnterIsPressed() {
        System.out.println();
        System.out.println();
        System.out.println("[ - press enter to continue                                                                      - ]");

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void battleLoop() {
        turn = 1;

        while (battle_active) {
            displayHealthStats();

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
                battle_active = false;
            } else if (enemy.getHealth() < 1) {
                // player wins
                battle_active = false;
            }

            if (turn == 1) {
                turn = 2;
            } else if (turn == 2) {
                turn = 1;
            }

            delayExecutionUntilEnterIsPressed();

            System.out.println("----------------------------------------------------------------------------------------------------");
        }

        // determine winner and end battle
        displayHealthStats();
        determineWinner();
    }

    public static void dungeonLoop() {
        generateLayer();
        displayDungeonInfo();
        initBattle();
        battleLoop();
    }
}
