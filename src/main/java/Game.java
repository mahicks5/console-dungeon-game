import java.util.Scanner;

public class Game {
    private static Character player;

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
        System.out.println("enter character name:");
        name = userInput.nextLine();

        boolean invalid = false;

        // ask user to choose a race
        String characterRace = "";

        while (!characterRace.equals("1") && !characterRace.equals("2") && !characterRace.equals("3")) {
            if (invalid) {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println();
            System.out.println("choose race:");
            System.out.println("1.) human");
            System.out.println("2.) orc");
            System.out.println("3.) elf");
            System.out.println();
            characterRace = userInput.nextLine();

            invalid = true;
        }

        invalid = false;

        race = switch (characterRace) {
            case "1" -> "human";
            case "2" -> "orc";
            case "3" -> "elf";
            default -> "human"; // default

        };

        // ask user to choose a role
        String characterRole = "";

        while (!characterRole.equals("1") && !characterRole.equals("2") && !characterRole.equals("3")) {
            if (invalid) {
                System.out.println("invalid choice. please choose again");
            }

            System.out.println();
            System.out.println("choose race:");
            System.out.println("1.) knight");
            System.out.println("2.) hunter");
            System.out.println("3.) wizard");
            System.out.println();
            characterRole = userInput.nextLine();

            invalid = true;
        }

        role = switch (characterRole) {
            case "1" -> "knight";
            case "2" -> "hunter";
            case "3" -> "wizard";
            default -> "knight"; // default

        };

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
        
        // standard build
        playerStats.setStatus("healthy");
        playerStats.setAttack(1);
        playerStats.setDefense(1);
        playerStats.setStamina(1);
        playerStats.setSpeed(1);

        playerInventory.setCoin(PlayerConstants.DEFAULT_COIN);
        
        switch (playerInfo.getRace()) {
            case "human" -> playerStats.increaseStamina();
            case "orc" -> playerStats.increaseAttack();
            case "elf" -> playerStats.increaseSpeed();
        };
        
        switch (playerInfo.getRole()) {
            case "knight" -> {
                playerStats.increaseAttack();

                Sword knightWeapon = new Sword();
                knightWeapon.setName("old sword");
                knightWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

                Alt knightAlt = new Alt();
                knightAlt.setName("punch");

                playerInventory.setWeapon(knightWeapon);
                playerInventory.setAlt(knightAlt);
            }
            case "hunter" -> {
                playerStats.increaseSpeed();

                Bow hunterWeapon = new Bow();
                hunterWeapon.setName("old bow");
                hunterWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

                Alt knightAlt = new Alt();
                knightAlt.setName("dagger");

                playerInventory.setWeapon(hunterWeapon);
                playerInventory.setAlt(knightAlt);
            }
            case "wizard" -> {
                playerStats.increaseStamina();

                Staff wizardWeapon = new Staff();
                wizardWeapon.setName("old staff");
                wizardWeapon.setAdditionalAttack(WeaponConstants.DEFAULT_WEAPON_ADDITIONAL_ATTACK);

                Alt knightAlt = new Alt();
                knightAlt.setName("quick spell");

                playerInventory.setWeapon(wizardWeapon);
                playerInventory.setAlt(knightAlt);
            }
        };
    }

    public static void displayCharacter() {
        CharacterInfo playerInfo = player.getCharacterInfo();
        CharacterStats playerStats = player.getCharacterStats();
        CharacterInventory playerInventory = player.getCharacterInventory();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("[ -      info      - ]");
        System.out.println("name     " + playerInfo.getName());
        System.out.println("race     " + playerInfo.getRace());
        System.out.println("role     " + playerInfo.getRole());
        System.out.println("level    " + playerStats.getLevel());

        System.out.println("[ -      stats     - ]");
        System.out.println("xp       " + playerStats.getXp());
        System.out.println("health   " + playerStats.getHealth());
        System.out.println("attack   " + playerStats.getAttack());
        System.out.println("defense  " + playerStats.getDefense());
        System.out.println("stamina  " + playerStats.getStamina());
        System.out.println("speed    " + playerStats.getSpeed());
        System.out.println("status   " + playerStats.getStatus());

        System.out.println("[ -    inventory   - ]");
        System.out.println("weapon   " + playerInventory.getWeapon().getName());
        System.out.println("alt      " + playerInventory.getAlt().getName());
//        System.out.println("armor    " + playerInventory.getArmor());
        System.out.println("coin     " + playerInventory.getCoin());
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);

        introMessage();
        promptCreateCharacter(userInput);
        buildStarterCharacter();
        displayCharacter();

        userInput.close();
    }
}