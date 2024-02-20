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
        System.out.println("[ --                                  Welcome to RPG Simulator                                  -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    public static void promptCreateCharacter(Scanner userInput) {
        String name;
        String race;
        String role;

        // ask user to name their character
        System.out.println("[ --                                    Create your character                                   -- ]");
        System.out.println("enter character name:");
        name = userInput.nextLine();

        boolean invalid = false;

        // ask user to choose a race
        int characterRace = 0;

        while (characterRace < 1 || characterRace > 3) {
            if (invalid) {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println();
            System.out.println("choose your race:");
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
            System.out.println("choose your role:");
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

        playerInventory.setCoin(PlayerConstants.DEFAULT_COIN);

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

    public static void displayCharacter(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ --                                    Character information                                   -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -      info      - ]");
        System.out.println("name     " + playerInfo.getName());
        System.out.println("race     " + playerInfo.getRace());
        System.out.println("role     " + playerInfo.getRole());
        System.out.println("level    " + playerStats.getLevel());

        System.out.println("[ -      stats     - ]");
        System.out.println("xp       " + playerStats.getXp());
        System.out.println(ConsoleColors.RED + "health   " + playerStats.getHealth() + ConsoleColors.DEFAULT);
        System.out.println("attack   " + playerStats.getAttack());
        System.out.println("defense  " + playerStats.getDefense());
        System.out.println("stamina  " + playerStats.getStamina());
        System.out.println("speed    " + playerStats.getSpeed());
        System.out.println("status   " + playerStats.getStatus());

        System.out.println();
        System.out.println();
        System.out.println("press enter to exit");

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void displayInventory(Scanner userInput) {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ --                                     Character inventory                                    -- ]");
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -     weapon     - ]");
        System.out.println(playerInventory.getWeapon().getClass().getName() + " " + playerInventory.getWeapon().getName());
        System.out.println("[ -       alt      - ]");
        System.out.println("alt      " + playerInventory.getAlt().getName());
        System.out.println("[ -      armor     - ]");
        ArrayList<Armor> armor = playerInventory.getArmor();

        for (Armor piece : armor) {
            System.out.println(piece.getClass().getName() + " " + piece.getName());
        }

        System.out.println("[ -       coin     - ]");
        System.out.println("coin     " + playerInventory.getCoin());

        System.out.println();
        System.out.println();
        System.out.println("press enter to exit");

        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayTreasure() {
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
    }

    public static void gameloop(Scanner userInput) {
        int selection = 0; // default; do nothing with 0

        int tick = 0;
        boolean gameActive = false;

        while (selection == 0) {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("[ --                                           Surface                                          -- ]");
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.println("choose your fate!");
            System.out.println("1.) enter the dungeon!");
            System.out.println("2.) visit shop");
            System.out.println("3.) display character");
            System.out.println("4.) display inventory");
            System.out.println();

            selection = userInput.nextInt();

            if (selection == 1) {
                gameManager.alert(player, "enter dungeon");
            }

            if (selection == 2) {
                // TODO
//                displayShop();
                displayTreasure();
                selection = 0;
            }

            if (selection == 3) {
                displayCharacter(userInput);
                selection = 0;
            }

            if (selection == 4) {
                displayInventory(userInput);
                selection = 0;
            }
        }
    }

    public static void recieve() {

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