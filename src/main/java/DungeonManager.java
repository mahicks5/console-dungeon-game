/**
 * DungeonManager.java
 * holds all the logic for anything in the dungeon. holds logic and display classes */

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DungeonManager {
    private static Enemy enemy = null;
    private static String active_event = "coin bonus";
    private static boolean battle_active = false;
    private static boolean player_moves_first;
    private static boolean tomeOfWealthActive;
    private static int tick = 1;
    private static int depth = 1;
    private static int turn = 1;

    public static void tick() {
        if (tick == 1) {
            tick = 2;
            active_event = EventNames.event2;
        } else if (tick == 2) {
            tick = 3;
            active_event = EventNames.event3;
        } else if (tick == 3) {
            tick = 4;
            active_event = EventNames.event4;
        } else if (tick == 4) {
            tick = 1;
            active_event = EventNames.event1;
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
        System.out.println(depth + " layer(s) below the surface");
        System.out.println(
                "active event: " +
                        ConsoleColors.YELLOW +
                        active_event +
                        ConsoleColors.DEFAULT
        );
        System.out.println();
//        System.out.println(
//                ConsoleColors.RED +
//                        enemy.getName() +
//                        " has appeared" +
//                        ConsoleColors.DEFAULT
//        );
//        System.out.println();
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


            enemy.setAttackName(enemyAttackGenerator.getRandomMinorEnemyAttack());

            double damage = EnemyConstants.MINOR_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        } else if (enemyDensity == 2) {
            enemy = enemyFactory.makeMajorEnemy();

            double health = EnemyConstants.MAJOR_ENEMY_BASE_HEALTH + (depth * EnemyConstants.DEPTH_HEALTH_BONUS);

            enemy.setHealth(health);


            enemy.setAttackName(enemyAttackGenerator.getRandomMajorEnemyAttack());

            double damage = EnemyConstants.MAJOR_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        } else if (enemyDensity == 3) {
            enemy = enemyFactory.makeBossEnemy();

            double health = EnemyConstants.BOSS_ENEMY_BASE_HEALTH + (depth * EnemyConstants.DEPTH_HEALTH_BONUS);

            enemy.setHealth(health);


            enemy.setAttackName(enemyAttackGenerator.getRandomBossEnemyAttack());

            double damage = EnemyConstants.BOSS_ENEMY_BASE_DAMAGE + (depth * EnemyConstants.DEPTH_DAMAGE_BONUS);

            enemy.setAttack(damage);
        }
    }

    public static void initBattle() {
        System.out.println();

        Random random = new Random();

        int playerSpeed = Game.getPlayer().getCharacterStats().getSpeed();

        int enemySpeed = random.nextInt(playerSpeed + 1);

        System.out.println(
                ConsoleColors.RED +
                        enemy.getName() +
                        " has appeared" +
                        ConsoleColors.DEFAULT
        );
        System.out.println();

        if (playerSpeed > enemySpeed) {
            player_moves_first = true;
            System.out.println("you have a higher speed. your move first.");
        } else if (playerSpeed < enemySpeed) {
            player_moves_first = false;
            System.out.println(enemy.getName() + " has a higher speed. " + enemy.getName() + " will move first.");
        }

        battle_active = true;
    }

    public static void playerTurn() {
        Scanner userInput = new Scanner(System.in);

        CharacterStats characterStats = Game.getPlayer().getCharacterStats();
        CharacterInventory characterInventory = Game.getPlayer().getCharacterInventory();

        int selection = 0;

        boolean altReady            = (characterInventory.getAlt().getCooldown() == 0);
        boolean healthPotionEnabled = (characterInventory.checkHealthPotions() > 0);
        boolean antidoteEnabled     = (
                (characterInventory.checkAntidotes() > 0) &&
                        (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.POISONED))
        );
        boolean tomeOfBanishment    = (characterInventory.checkTome(1) > 0);
        boolean tomeOfWealth        = (
                (characterInventory.checkTome(2) > 0) &&
                        (!tomeOfWealthActive)
        );
        boolean tomeOfBlessing      = (
                (characterInventory.checkTome(3) > 0) &&
                        (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.CURSED))
        );
        boolean fleeEnabled         = ((depth % 10) == 0);

        boolean invalidChoice = false;


        while (selection == 0) {
            if (!invalidChoice) {
                System.out.println("make a choice");
            } else {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println(
                    "1.) use " +
                    ConsoleColors.BLUE +
                            characterInventory.getWeapon().getName() +
                            ConsoleColors.DEFAULT
            );

            if (altReady) {
                System.out.println(
                        "2.) use " +
                                ConsoleColors.BLUE +
                                characterInventory.getAlt().getName() +
                                ConsoleColors.DEFAULT
                );
            }

            if (healthPotionEnabled) {
                System.out.println(
                        "3.) use " +
                                ConsoleColors.BLUE +
                                "health potion" +
                                ConsoleColors.DEFAULT +
                                " (" +
                                ConsoleColors.BLUE +
                                characterInventory.checkHealthPotions() +
                                ConsoleColors.DEFAULT +
                                " remaining)"
                );
            }

            if (antidoteEnabled) {
                System.out.println(
                                "4.) use " +
                                ConsoleColors.BLUE +
                                "antidote" +
                                ConsoleColors.DEFAULT +
                                " (" +
                                ConsoleColors.BLUE +
                                characterInventory.checkAntidotes() +
                                ConsoleColors.DEFAULT +
                                " remaining)"
                );
            }

            if (tomeOfBanishment) {
                System.out.println(
                                "5.) use " +
                                ConsoleColors.BLUE +
                                "tome of banishment" +
                                ConsoleColors.DEFAULT +
                                " (" +
                                ConsoleColors.BLUE +
                                characterInventory.checkTome(1) +
                                ConsoleColors.DEFAULT +
                                " remaining)"
                );
            }

            if (tomeOfWealth) {
                System.out.println(
                                "6.) use " +
                                ConsoleColors.BLUE +
                                "tome of wealth" +
                                ConsoleColors.DEFAULT +
                                " (" +
                                ConsoleColors.BLUE +
                                characterInventory.checkTome(2) +
                                ConsoleColors.DEFAULT +
                                " remaining)"
                );
            }


            if (tomeOfBlessing) {
                System.out.println(
                                "7.) use " +
                                ConsoleColors.BLUE +
                                "tome of blessing" +
                                ConsoleColors.DEFAULT +
                                " (" +
                                ConsoleColors.BLUE +
                                characterInventory.checkTome(3) +
                                ConsoleColors.DEFAULT +
                                " remaining)"
                );
            }

            if (fleeEnabled) {
                System.out.println("8.) flee");
            }

            int choice = userInput.nextInt();

            if ((choice == 2) && (!altReady)) {
                invalidChoice = true;
            } else if ((choice == 3) && (!healthPotionEnabled)) {
                invalidChoice = true;
            } else if ((choice == 4) && (!fleeEnabled)) {
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
            if (active_event.equals(EventNames.event4)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            }

            // chance to miss. includes event
            if (active_event.equals(EventNames.event3)) {
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
            if (active_event.equals(EventNames.event4)) {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            } else {
                boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE);

                if (chance) {
                    System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                    System.out.println();

                    damage *= 2;
                }
            }

            // chance to miss. includes event
            if (active_event.equals(EventNames.event3)) {
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

        if ((selection == 3) && (healthPotionEnabled)) {
            System.out.println();
            System.out.println(
                    ConsoleColors.CYAN +
                            "you start to feel better..." +
                            ConsoleColors.DEFAULT
            );

            characterInventory.useHealthPotion();
            characterStats.heal();
        }

        if ((selection == 4) && (antidoteEnabled) && (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.POISONED))) {
            System.out.println();
            System.out.println(
                    ConsoleColors.CYAN +
                            "the poison has faded away..." +
                    ConsoleColors.DEFAULT
            );

            characterInventory.useAntidotePotion();
            characterStats.setStatus(StatusEffects.HEALTHY);
        }

        if ((selection == 5) && (tomeOfBanishment)) {
            System.out.println();
            System.out.println(
                    ConsoleColors.CYAN +
                            "a rumble is felt as " +
                            ConsoleColors.RED +
                            enemy.getName() +
                            ConsoleColors.CYAN +
                            " disappears in the blink of an eye..." +
                            ConsoleColors.DEFAULT
            );

            characterInventory.useTome(1);
            enemy.takeDamage(999999999.0);
        }

        if ((selection == 6) && (tomeOfWealth)) {
            System.out.println(
                    ConsoleColors.CYAN +
                            "you start to feel very lucky..." +
                            ConsoleColors.DEFAULT
            );

            characterInventory.useTome(2);
            tomeOfWealthActive = true;
        }

        if ((selection == 7) && (tomeOfBlessing) && (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.CURSED))) {
            System.out.println(
                    ConsoleColors.CYAN +
                            "a burden lifts off your shoulders..." +
                            ConsoleColors.DEFAULT
            );

            characterInventory.useTome(3);
            characterStats.setStatus(StatusEffects.HEALTHY);
        }

        if ((selection == 8) && (fleeEnabled)) {
            battle_active = false;
            turn = 1;

            System.out.println(
                    ConsoleColors.CYAN +
                            "you run as fast as you can..." +
                            ConsoleColors.DEFAULT
            );

            System.out.println();
            delayExecutionUntilEnterIsPressed();

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
        if (active_event.equals(EventNames.event4)) {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE * EventConstants.INCREASED_CRITICAL_CHANCE);

            if (chance) {
                System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 2;
            }
        } else {
            boolean chance = rng.roll(DungeonConstants.DEFAULT_CRITICAL_CHANCE);

            if (chance) {
                System.out.println(ConsoleColors.CYAN + "critical!" + ConsoleColors.DEFAULT);
                System.out.println();

                damage *= 2;
            }
        }

        // chance to miss. includes event
        if (active_event.equals(EventNames.event3)) {
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
                            ConsoleColors.PURPLE + enemy.getAttackName() + ConsoleColors.DEFAULT
            );

            System.out.println();

            System.out.println(
                    "you take " + ConsoleColors.PURPLE + damage + ConsoleColors.DEFAULT +
                            " of damage"
            );

            Game.getPlayer().getCharacterStats().takeDamage(damage);

            boolean roll = rng.roll(DungeonConstants.DEFAULT_STATUS_CHANCE);

            if (roll) {
                roll = rng.roll(1.0 / 2.0);

                if (roll) {
                    Game.getPlayer().getCharacterStats().setStatus(StatusEffects.POISONED);
                } else {
                    Game.getPlayer().getCharacterStats().setStatus(StatusEffects.CURSED);
                }
            }
        }

        // end critical and miss modifiers

        System.out.println();
        System.out.println();
    }

    public static void displayHealthStats() {
        System.out.println(
                ConsoleColors.GREEN +
                        Game.getPlayer().getCharacterInfo().getName() +
                        ConsoleColors.DEFAULT +
                        " health: " +
                        ConsoleColors.GREEN +
                        Game.getPlayer().getCharacterStats().getHealth() +
                        ConsoleColors.DEFAULT
        );

        System.out.println(
                ConsoleColors.RED +
                        enemy.getName() +
                        ConsoleColors.DEFAULT +
                        " health: " +
                        ConsoleColors.RED +
                        enemy.getHealth() +
                        ConsoleColors.DEFAULT
        );

        if (!Game.getPlayer().getCharacterStats().getStatus().equals("healthy")) {
            System.out.println();
            System.out.println(
                    ConsoleColors.RED +
                    Game.getPlayer().getCharacterStats().getStatus() +
                            ConsoleColors.DEFAULT
            );
        }

        System.out.println();
    }

    public static void rewardPlayer() {
        CharacterStats characterStats = Game.getPlayer().getCharacterStats();
        CharacterInventory characterInventory = Game.getPlayer().getCharacterInventory();

        int coin = DungeonConstants.DEFAULT_COIN_REWARD + (depth * DungeonConstants.DEPTH_COIN_BONUS);
        int xp = DungeonConstants.DEFAULT_XP_REWARD + (depth * DungeonConstants.DEPTH_XP_BONUS);

        if (active_event.equals(EventNames.event1)) {
            coin *= EventConstants.BONUS_COINS;
        }

        if (active_event.equals(EventNames.event2)) {
            xp *= EventConstants.BONUS_XP;
        }

        if (tomeOfWealthActive) {
            coin *= TomeConstants.TOME_OF_WEALTH_BONUS;
        }

        tomeOfWealthActive = false;

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

        if (active_event.equals(EventNames.event1)) {
            coin *= EventConstants.BONUS_COINS;
        }

        characterInventory.addCoin(coin);

        for (int i = 0; i < potions; i++){
            characterInventory.addHealthPotion();
        }

        System.out.println();
        System.out.println(
                "you found " +
                        ConsoleColors.YELLOW +
                        coin +
                        ConsoleColors.DEFAULT +
                        " coin and " +
                        ConsoleColors.BLUE +
                        potions + " potions" +
                        ConsoleColors.DEFAULT
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

    public static void curseCheck() {
        if (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.POISONED)) {
            Game.getPlayer().getCharacterStats().takeDamage(StatusConstants.POISON_DAMAGE);
            System.out.println(ConsoleColors.PURPLE +
                    "you took " + StatusConstants.POISON_DAMAGE + " of damage to poison" +
                    ConsoleColors.DEFAULT
            );
            System.out.println();
        }

        if (Game.getPlayer().getCharacterStats().getStatus().equals(StatusEffects.CURSED)) {
            Game.getPlayer().getCharacterInventory().spendCoin(StatusConstants.CURSE_COIN_LOSS);
            System.out.println(ConsoleColors.PURPLE +
                    "you lost " + StatusConstants.CURSE_COIN_LOSS + " coins to curse of loss" +
                    ConsoleColors.DEFAULT
            );
            System.out.println();
        }
    }

    public static void battleLoop() {
        turn = 1;

        while (battle_active) {
            displayDungeonInfo();
            displayHealthStats();
            curseCheck();

            if ((player_moves_first) && (turn == 1)) {
                // player move
                playerTurn();
            } else if ((!player_moves_first) && (turn == 1)) {
                // computer turn
                computerMove();
            } else if ((!player_moves_first) && (turn == 2)) {
                // player move
                playerTurn();
            } else if ((player_moves_first) && (turn == 2)) {
                // computer turn
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
        initBattle();
        battleLoop();
    }
}
