/**
 * Game.java main game class
 * This runs the game and hold the logic for navigating the game
 * */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static Character player;
    private static GameManager gameManager;

    public static Character getPlayer() {
        return player;
    }

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
        int characterRole = 0;

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
     * build the player's default character based off of what they chose for their race and role
     * */
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
    }

    public static void displayCharacterInfo(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- information                                                                                -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("name     " + playerInfo.getName());
        System.out.println("race     " + playerInfo.getRace());
        System.out.println("role     " + playerInfo.getRole());

        delayExecutionUntilEnterIsPressed();
    }
    public static void displayCharacterStats(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- stats                                                                                      -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("level    " + playerStats.getLevel());
        System.out.println("xp       " + playerStats.getXp());
        System.out.println();

        System.out.println("stat pts " + playerStats.getStatPoints());
        System.out.println();

        System.out.println(ConsoleColors.GREEN + "health   " + playerStats.getHealth() + ConsoleColors.DEFAULT);
        System.out.println();

        System.out.println("attack   " + playerStats.getAttack());
        System.out.println("defense  " + playerStats.getDefense());
        System.out.println("stamina  " + playerStats.getStamina());
        System.out.println("speed    " + playerStats.getSpeed());
        System.out.println();

        System.out.println("status   " + playerStats.getStatus());

        delayExecutionUntilEnterIsPressed();
    }

    public static void displayStatUpgradeScreen(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

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
            System.out.println("attack   " + playerStats.getAttack());
            System.out.println("defense  " + playerStats.getDefense());
            System.out.println("stamina  " + playerStats.getStamina());
            System.out.println("speed    " + playerStats.getSpeed());

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
                    System.out.println("you can feel yourself grow healthier");
                    System.out.println();

                    playerStats.consumeStatPoint();
                    playerStats.increaseAttack();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }

            if (selection == 2) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow stronger");
                    System.out.println();

                    playerStats.consumeStatPoint();
                    playerStats.increaseDefense();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }

            if (selection == 3) {
                if (playerStats.getStatPoints() > 0) {
                    System.out.println("you can feel yourself grow resilient");
                    System.out.println();

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
                    System.out.println("you can feel yourself grow faster");
                    System.out.println();

                    playerStats.consumeStatPoint();
                    playerStats.increaseSpeed();

                    selection = 0;
                } else {
                    System.out.println("not enough stat points");
                    System.out.println();
                }
            }
        }

        delayExecutionUntilEnterIsPressed();
    }

    public static void displayInventory(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -- inventory                                                                                  -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ - weapon                                                                                       - ]");
        System.out.println(playerInventory.getWeapon().getClass().getName() + " " + playerInventory.getWeapon().getName());
        System.out.println();

        System.out.println("[ - alt                                                                                          - ]");
        System.out.println("alt      " + playerInventory.getAlt().getName());
        System.out.println();

        System.out.println("[ - armor                                                                                        - ]");
        ArrayList<Armor> armor = playerInventory.getArmor();

        for (Armor piece : armor) {
            System.out.println(piece.getName());
        }
        System.out.println();

        System.out.println("[ - coin                                                                                         - ]");
        System.out.println("coin     " + ConsoleColors.YELLOW + playerInventory.getCoin() + ConsoleColors.DEFAULT);
        System.out.println();

        System.out.println("[ - potions                                                                                      - ]");
        System.out.println("health potions      " + playerInventory.checkPotions());
        System.out.println("antidote            ");
        System.out.println();

        System.out.println("[ - spell tomes                                                                                  - ]");
        System.out.println("3.) tome of banishment   ");
        System.out.println("4.) tome of wealth       ");
        System.out.println("5.) tome of experience   ");
        System.out.println();

        delayExecutionUntilEnterIsPressed();
    }

    public static void displayShop(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        int selection = 0; // default; do nothing with 0

        while (selection != 6) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ -- ragnar, man of many-trinkets                                                               -- ]");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("what're ya buyin'?");
            System.out.println();

            System.out.println("[ - potions                                                                                      - ]");
            System.out.println("1.) health potion        " + ConsoleColors.YELLOW + ShopConstants.HEALTH_POTION_COST + ConsoleColors.DEFAULT);
            System.out.println("2.) antidote             " + ConsoleColors.YELLOW + ShopConstants.ANTIDOTE_POTION_COST + ConsoleColors.DEFAULT);
            System.out.println();

            System.out.println("[ - spell tomes                                                                                  - ]");
            System.out.println("3.) tome of banishment   " + ConsoleColors.YELLOW + ShopConstants.TOME_OF_BANISHMENT_COST + ConsoleColors.DEFAULT);
            System.out.println("4.) tome of wealth       " + ConsoleColors.YELLOW + ShopConstants.TOME_OF_WEALTH_COST + ConsoleColors.DEFAULT);
            System.out.println("5.) tome of experience   " + ConsoleColors.YELLOW + ShopConstants.TOME_OF_EXPERIENCE_COST + ConsoleColors.DEFAULT);
            System.out.println();

            System.out.println("[ - coins                                                                                        - ]");
            System.out.println(ConsoleColors.YELLOW + playerInventory.getCoin() + ConsoleColors.DEFAULT);
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
                // TODO
            }

            if (selection == 3) {

            }

            if (selection == 4) {

            }

            if (selection == 5) {

            }
        }

        delayExecutionUntilEnterIsPressed();
    }

    public static void compoundLevels() {
        CharacterStats characterStats = player.getCharacterStats();
        CharacterInventory characterInventory = player.getCharacterInventory();

        int level = (characterStats.getXp() / PlayerConstants.LEVEL_CAP);

        int additionalXp = characterStats.getXp() - (level * PlayerConstants.LEVEL_CAP);

        characterStats.consumeXp();
        characterStats.addXp(additionalXp);

        if (level > 0) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
        }

        // give players stat points to spend
        for (int i = 0; i < level; i++) {
            System.out.println("level up! stat point earned");
            characterStats.addLevel();
            characterStats.addStatPoint();
        }
    }

    public static void gameloop(Scanner userInput) {
        int selection = 0; // default; do nothing with 0

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
                displayCharacterInfo(userInput);
                selection = 0;
            }

            if (selection == 5) {
                displayCharacterStats(userInput);
                selection = 0;
            }

            if (selection == 6) {
                displayInventory(userInput);
                selection = 0;
            }
        }
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