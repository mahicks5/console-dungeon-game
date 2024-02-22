/**
 * Game.java main game class
 * This runs the game and hold the logic for navigating the game */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static Character player;
    private static GameManager gameManager;

    public static void setPlayer(Character player) {
        Game.player = player;
    }

    public static Character getPlayer() {
        return player;
    }

    /**
     * Displays an intro message. */
    public static void introMessage() {
        // let's clear some space :)
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- welcome to the dark dungeons                                                               -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    /**
     * Prompts the player with all they need to create a new character. */
    public static void promptCreateCharacter(Scanner userInput) {
        String name;
        String race;
        String role;

        // ask user to name their character
        System.out.println("[ -- character builder                                                                          -- ]");
        System.out.println("[ - enter a name                                                                                 - ]");
        name = userInput.nextLine();

        boolean invalid = false;

        // ask user to choose a race
        int characterRace = 0;
        int characterRole = 0;


        if (name.equals("devmaxwell")) {
            characterRace = 1;
            characterRole = 1;
        }

        while (characterRace < 1 || characterRace > 3) {
            if (invalid) {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println();
            System.out.println("[ - choose a race                                                                                - ]");
            System.out.println("1.) human");
            System.out.println("2.) orc");
            System.out.println("3.) elf");
            System.out.println();
            characterRace = userInput.nextInt();

            invalid = true;
        }

        invalid = false;

        race = "human"; // fallback

        if (characterRace == 1) {
            race = "human";
        }

        if (characterRace == 2) {
            race = "orc";
        }

        if (characterRace == 3) {
            race = "elf";
        }

        // ask user to choose a role

        while (characterRole < 1 || characterRole > 3) {
            if (invalid) {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println();
            System.out.println("[ - choose your role                                                                             - ]");
            System.out.println("1.) knight");
            System.out.println("2.) hunter");
            System.out.println("3.) wizard");
            System.out.println();
            characterRole = userInput.nextInt();

            invalid = true;
        }

        role = "knight";

        if (characterRole == 1) {
            role = "knight";
        }

        if (characterRole == 2) {
            role = "hunter";
        }

        if (characterRole == 3) {
            role = "wizard";
        }

        player = new Character();
        CharacterInfo playerInfo = player.getCharacterInfo();

        playerInfo.setName(name);
        playerInfo.setRace(race);
        playerInfo.setRole(role);
    }

    /**
     * build the player's default character based off of what they chose for their race and role. */
    public static void buildStarterCharacter() {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        // create weapon factory
        WeaponFactory weaponFactory = new WeaponFactory();

        // create armor factory
        ArmorFactory armorFactory = new ArmorFactory();

        // standard build
        playerStats.setStatus("healthy");
        playerStats.setAttack(1);
        playerStats.setDefense(1);
        playerStats.setStamina(1);
        playerStats.setSpeed(1);

        playerInventory.addCoin(PlayerConstants.DEFAULT_COIN);
        playerInventory.addHealthPotion();
        playerInventory.addHealthPotion();

        if (playerInfo.getRace().equals("human")) {
            playerStats.increaseStamina();
        }

        if (playerInfo.getRace().equals("orc")) {
            playerStats.increaseAttack();
        }

        if (playerInfo.getRace().equals("elf")) {
            playerStats.increaseSpeed();
        }

        if (playerInfo.getRole().equals("knight")) {
            playerStats.increaseAttack();
            playerStats.increaseDefense();

            // create weapons
            Sword knightWeapon = weaponFactory.makeSword();
            knightWeapon.setName("old sword");
            knightWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

            Alt knightAlt = new Alt();
            knightAlt.setName("punch");

            playerInventory.setWeapon(knightWeapon);
            playerInventory.setAlt(knightAlt);

            // create armor
            ChestArmor chest = armorFactory.makeChestArmor();
            chest.setName("haggard shirt");
            chest.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor pants = armorFactory.makeLegArmor();
            pants.setName("haggard pants");
            pants.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor boots = armorFactory.makeBootArmor();
            boots.setName("haggard boots");
            boots.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            playerInventory.setArmor(chest);
            playerInventory.setArmor(pants);
            playerInventory.setArmor(boots);
        }

        if (playerInfo.getRole().equals("hunter")) {
            playerStats.increaseSpeed();
            playerStats.increaseStamina();

            // create weapons
            Bow hunterWeapon = weaponFactory.makeBow();
            hunterWeapon.setName("old bow");
            hunterWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

            Alt knightAlt = new Alt();
            knightAlt.setName("dagger");

            playerInventory.setWeapon(hunterWeapon);
            playerInventory.setAlt(knightAlt);

            // create armor
            ChestArmor tunic = armorFactory.makeChestArmor();
            tunic.setName("haggard tunic");
            tunic.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor pants = armorFactory.makeLegArmor();
            pants.setName("haggard pants");
            pants.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor boots = armorFactory.makeBootArmor();
            boots.setName("haggard boots");
            boots.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            playerInventory.setArmor(tunic);
            playerInventory.setArmor(pants);
            playerInventory.setArmor(boots);
        }

        if (playerInfo.getRole().equals("wizard")) {
            playerStats.increaseStamina();
            playerStats.increaseAttack();

            // create weapons
            Staff wizardWeapon = weaponFactory.makeStaff();
            wizardWeapon.setName("old staff");
            wizardWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

            Alt knightAlt = new Alt();
            knightAlt.setName("quick spell");

            playerInventory.setWeapon(wizardWeapon);
            playerInventory.setAlt(knightAlt);

            // create armor
            HeadArmor hat = armorFactory.makeHeadArmor();
            hat.setName("haggard hat");
            hat.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor robe = armorFactory.makeChestArmor();
            robe.setName("haggard robe");
            robe.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            Armor sandals = armorFactory.makeBootArmor();
            sandals.setName("haggard sandals");
            sandals.setResistance(ArmorConstants.DEFAULT_ARMOR_RESISTANCE);

            playerInventory.setArmor(hat);
            playerInventory.setArmor(robe);
            playerInventory.setArmor(sandals);
        }

        if (playerInfo.getName().equals("devmaxwell")) {
            int maxStat = 50000;
            int maxCoin = 999999999;
            int maxItemAmount = 999;

            playerStats.setHealth(maxStat);
            playerStats.setAttack(maxStat);
            playerStats.setDefense(maxStat);
            playerStats.setSpeed(maxStat);
            playerStats.setStamina(maxStat);

            playerInventory.addCoin(maxCoin);

            for (int i = 0; i < maxItemAmount; i++) {
                playerInventory.addHealthPotion();
                playerInventory.addAntidotesPotion();
                playerInventory.addTome(1);
                playerInventory.addTome(2);
                playerInventory.addTome(3);
            }
        }
    }

    /**
     * Displays the player's character's information. */
    public static void displayCharacterInfo() {
        CharacterInfo playerInfo = player.getCharacterInfo();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- information                                                                                -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(
                ConsoleColors.GREEN
                        + "name                                              "
                        + playerInfo.getName()
                        + ConsoleColors.DEFAULT
        );
        System.out.println(
                "race                                              "
                + playerInfo.getRace()
        );
        System.out.println(
                "role                                              "
                        + playerInfo.getRole()
        );

        delayExecutionUntilEnterIsPressed();
    }

    /**
     * Displays the player's character's stats. */
    public static void displayCharacterStats() {
        CharacterStats playerStats = player.getCharacterStats();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- stats                                                                                      -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println(
                "level                                             "
                + playerStats.getLevel()
        );
        System.out.println(
                "xp                                                "
                + playerStats.getXp()
        );
        System.out.println();

        System.out.println(
                "stat pts "
                + playerStats.getStatPoints()
        );
        System.out.println();

        System.out.println(ConsoleColors.GREEN
                + "health                                            "
                + playerStats.getHealth()
                + ConsoleColors.DEFAULT
        );
        System.out.println();

        System.out.println(
                "attack                                            "
                + playerStats.getAttack()
        );
        System.out.println(
                "defense                                           "
                + playerStats.getDefense()
        );
        System.out.println(
                "stamina                                           "
                + playerStats.getStamina()
        );
        System.out.println(
                "speed                                             "
                + playerStats.getSpeed()
        );
        System.out.println();

        System.out.println(
                "status                                            "
                + playerStats.getStatus()
        );

        delayExecutionUntilEnterIsPressed();
    }

    /**
     * Displays the stat upgrade screen. */
    public static void displayStatUpgradeScreen(Scanner userInput) {
        CharacterStats playerStats = player.getCharacterStats();

        int selection = 0;

        while (selection != 5) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ -- upgrade stats                                                                              -- ]");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ - stat points                                                                                  - ]");
            System.out.println(playerStats.getStatPoints());
            System.out.println();

            System.out.println("[ - stats                                                                                        - ]");
            System.out.println(
                    "attack                                            "
                    + playerStats.getAttack()
            );
            System.out.println(
                    "defense                                           "
                    + playerStats.getDefense()
            );
            System.out.println(
                    "stamina                                           "
                    + playerStats.getStamina()
            );
            System.out.println(
                    "speed                                             "
                    + playerStats.getSpeed()
            );

            System.out.println();
            System.out.println("1.) attack");
            System.out.println("2.) defense");
            System.out.println("3.) stamina");
            System.out.println("4.) speed");
            System.out.println("5.) done");
            System.out.println();

            selection = userInput.nextInt();

            if (selection == 1) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow stronger..");
                    System.out.println();

                    playerStats.consumeStatPoint();
                    playerStats.increaseAttack();

                    delayExecutionUntilEnterIsPressed();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();

                    delayExecutionUntilEnterIsPressed();
                }
            }

            if (selection == 2) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow more resilient..");
                    System.out.println();

                    playerStats.consumeStatPoint();
                    playerStats.increaseDefense();

                    delayExecutionUntilEnterIsPressed();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }

            if (selection == 3) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow more able..");
                    System.out.println();

                    delayExecutionUntilEnterIsPressed();

                    playerStats.consumeStatPoint();
                    playerStats.increaseStamina();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }

            if (selection == 4) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow faster..");
                    System.out.println();

                    delayExecutionUntilEnterIsPressed();

                    playerStats.consumeStatPoint();
                    playerStats.increaseSpeed();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }
        }
    }

    /**
     * Displays the player's inventory. */
    public static void displayInventory() {
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- inventory                                                                                  -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ - weapon                                                                                       - ]");
        System.out.println(
                ConsoleColors.BLUE
                        + playerInventory.getWeapon().getName()
                        + ConsoleColors.DEFAULT
        );
        System.out.println();

        System.out.println("[ - alt                                                                                          - ]");
        System.out.println(
                ConsoleColors.BLUE
                        + playerInventory.getAlt().getName()
                        + ConsoleColors.DEFAULT
        );
        System.out.println();

        System.out.println("[ - armor                                                                                        - ]");
        ArrayList<Armor> armor = playerInventory.getArmor();

        for (Armor piece : armor) {
            System.out.println(
                    ConsoleColors.BLUE
                            + piece.getName()
                            + ConsoleColors.DEFAULT
            );
        }
        System.out.println();

        System.out.println("[ - coin                                                                                         - ]");
        System.out.println(
                ConsoleColors.YELLOW
                        + playerInventory.getCoin()
                        + ConsoleColors.DEFAULT
        );
        System.out.println();

        System.out.println("[ - potions                                                                                      - ]");
        System.out.println(
                ConsoleColors.BLUE
                        + "health potions                                    "
                        + playerInventory.getHealthPotions()
                        + ConsoleColors.DEFAULT
        );
        System.out.println(
                ConsoleColors.BLUE
                        + "antidotes                                         "
                        + playerInventory.getAntidotes()
                        + ConsoleColors.DEFAULT
        );
        System.out.println();

        System.out.println("[ - spell tomes                                                                                  - ]");
        System.out.println(
                ConsoleColors.BLUE
                        + "tome of banishment                                "
                        + playerInventory.checkTome(1)
                        + ConsoleColors.DEFAULT
        );
        System.out.println(
                ConsoleColors.BLUE
                        + "tome of wealth                                    "
                        + playerInventory.checkTome(2)
                        + ConsoleColors.DEFAULT
        );
        System.out.println(
                ConsoleColors.BLUE
                        + "tome of blessing                                  "
                        + playerInventory.checkTome(3)
                        + ConsoleColors.DEFAULT
        );
        System.out.println();

        delayExecutionUntilEnterIsPressed();
    }

    public static void displayShop(Scanner userInput) {
        CharacterInventory playerInventory = player.getCharacterInventory();

        int selection = 0;

        while (selection != 6) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ -- ragnar, man of many-trinkets                                                               -- ]");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("ragnar: what're ya buyin'?");
            System.out.println();

            System.out.println("[ - potions                                                                                      - ]");
            System.out.println(
                    "1.) health potion        "
                    + ConsoleColors.YELLOW
                    + ShopConstants.HEALTH_POTION_COST
                    + ConsoleColors.DEFAULT
            );
            System.out.println(
                    "2.) antidote             "
                    + ConsoleColors.YELLOW
                    + ShopConstants.ANTIDOTE_POTION_COST
                    + ConsoleColors.DEFAULT
            );
            System.out.println();

            System.out.println("[ - spell tomes                                                                                  - ]");
            System.out.println(
                    "3.) tome of banishment   "
                    + ConsoleColors.YELLOW
                    + ShopConstants.TOME_OF_BANISHMENT_COST
                    + ConsoleColors.DEFAULT
            );
            System.out.println(
                    "4.) tome of wealth       "
                            + ConsoleColors.YELLOW
                            + ShopConstants.TOME_OF_WEALTH_COST
                            + ConsoleColors.DEFAULT
            );
            System.out.println(
                    "5.) tome of blessing     "
                            + ConsoleColors.YELLOW
                            + ShopConstants.TOME_OF_BLESSING
                            + ConsoleColors.DEFAULT
            );
            System.out.println();

            System.out.println("[ - coins                                                                                        - ]");
            System.out.println(
                    ConsoleColors.YELLOW
                    + playerInventory.getCoin()
                    + ConsoleColors.DEFAULT
            );
            System.out.println();

            System.out.println("6.) exit shop");
            System.out.println();

            selection = userInput.nextInt();

            if (selection == 1) {
                if (playerInventory.getCoin() >= ShopConstants.HEALTH_POTION_COST) {
                    System.out.println();
                    System.out.println("ah. good one there.");

                    playerInventory.spendCoin(ShopConstants.HEALTH_POTION_COST);
                    playerInventory.addHealthPotion();

                    delayExecutionUntilEnterIsPressed();
                } else {
                    System.out.println();
                    System.out.println("not enough coin, stranger.");

                    delayExecutionUntilEnterIsPressed();
                }
            }

            if (selection == 2) {
                if (playerInventory.getCoin() >= ShopConstants.ANTIDOTE_POTION_COST) {
                    System.out.println();
                    System.out.println("ah. good one there.");

                    playerInventory.spendCoin(ShopConstants.ANTIDOTE_POTION_COST);
                    playerInventory.addAntidotesPotion();

                    delayExecutionUntilEnterIsPressed();
                } else {
                    System.out.println();
                    System.out.println("not enough coin, stranger.");

                    delayExecutionUntilEnterIsPressed();
                }
            }

            if (selection == 3) {
                if (playerInventory.getCoin() >= ShopConstants.TOME_OF_BANISHMENT_COST) {
                    System.out.println();
                    System.out.println("ah. good one there.");

                    playerInventory.spendCoin(ShopConstants.TOME_OF_BANISHMENT_COST);
                    playerInventory.addTome(1);

                    delayExecutionUntilEnterIsPressed();
                } else {
                    System.out.println();
                    System.out.println("not enough coin, stranger.");

                    delayExecutionUntilEnterIsPressed();
                }
            }

            if (selection == 4) {
                if (playerInventory.getCoin() >= ShopConstants.TOME_OF_WEALTH_COST) {
                    System.out.println();
                    System.out.println("ah. good one there.");

                    playerInventory.spendCoin(ShopConstants.TOME_OF_WEALTH_COST);
                    playerInventory.addTome(2);

                    delayExecutionUntilEnterIsPressed();
                } else {
                    System.out.println();
                    System.out.println("not enough coin, stranger.");

                    delayExecutionUntilEnterIsPressed();
                }
            }

            if (selection == 5) {
                if (playerInventory.getCoin() >= ShopConstants.TOME_OF_BLESSING) {
                    System.out.println();
                    System.out.println("ah. good one there.");

                    playerInventory.spendCoin(ShopConstants.TOME_OF_BLESSING);
                    playerInventory.addTome(3);

                    delayExecutionUntilEnterIsPressed();
                } else {
                    System.out.println();
                    System.out.println("not enough coin, stranger.");

                    delayExecutionUntilEnterIsPressed();
                }
            }
        }

        delayExecutionUntilEnterIsPressed();
    }

    /**
     * Controls the leveling up process. */
    public static void compoundLevels() {
        CharacterStats characterStats = player.getCharacterStats();

        int levelCap = PlayerConstants.BASE_LEVEL_CAP + (characterStats.getLevel() * PlayerConstants.BASE_LEVEL_CAP);

        int left = (characterStats.getXp() % levelCap);

        int level = (characterStats.getXp() / levelCap);

        characterStats.consumeXp();
        characterStats.addXp(left);

        if (level > 0) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
        }

        // give players stat points to spend
        for (int i = 0; i < level; i++) {
            System.out.println("level up! stat point earned");
            characterStats.levelUp();
            characterStats.addStatPoint();
        }
    }

    /**
     * main game loop. */
    public static void gameloop(Scanner userInput) {
        int selection = 0;

        compoundLevels();

        while (selection == 0) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ -- surface                                                                                    -- ]");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ - choose your fate                                                                             - ]");
            System.out.println("1.) enter the dungeon!");
            System.out.println("2.) visit shop");
            System.out.println("3.) upgrade stats");
            System.out.println("4.) display character info");
            System.out.println("5.) display character stats");
            System.out.println("6.) display inventory");
            System.out.println();

            player.getCharacterStats().heal();

            selection = userInput.nextInt();

            if (selection == 1) {
                gameManager.alert(player, "enter dungeon");
            }

            if (selection == 2) {
                displayShop(userInput);
                selection = 0;
            }

            if (selection == 3) {
                displayStatUpgradeScreen(userInput);
                selection = 0;
            }

            if (selection == 4) {
                displayCharacterInfo();
                selection = 0;
            }

            if (selection == 5) {
                displayCharacterStats();
                selection = 0;
            }

            if (selection == 6) {
                displayInventory();
                selection = 0;
            }
        }
    }

    /**
     * delays execution until enter is pressed. */
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

    /**
     * Where the game in launched through. It all starts here. */
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        gameManager = new GameManager();

        introMessage();
        promptCreateCharacter(userInput);
        buildStarterCharacter();

        gameManager.alert(player, "player init");
        gameManager.alert(player, "init health");

        gameloop(userInput);

        userInput.close();
    }
}