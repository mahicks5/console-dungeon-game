/**
 * CharacterInventory.java class that holds the character inventory. */

import java.util.ArrayList;

public class CharacterInventory {
    private Weapon weapon;
    private Alt alt;
    private final ArrayList<Armor> armor = new ArrayList<>();
    private int coin;
    private int healthPotions;
    private int antidotes;
    private int tomeOfBanishment = 0;
    private int tomeOfWealth = 0;
    private int tomeOfBlessing = 0;

    public int getHealthPotions() {
        return this.healthPotions;
    }

    public void addHealthPotion() {
        this.healthPotions += 1;
    }

    /**
     * Consumes a health potion. */
    public void useHealthPotion() {
        if (this.healthPotions > 0) {
            this.healthPotions -= 1;
        }
    }

    /**
     * Returns the amount of antidotes the player has.
     * @return antidotes */
    public int getAntidotes() {
        return this.antidotes;
    }

    /**
     * Adds an antidote. */
    public void addAntidotesPotion() {
        this.antidotes += 1;
    }

    /**
     * Consumes an antidote. */
    public void useAntidotePotion() {
        if (this.antidotes > 0) {
            this.antidotes -= 1;
        }
    }

    /**
     * Checks if the player has enough of the requested tome.
     * @return quantity of requested tome. */
    public int checkTome(int choice) {
        if (choice == 1) {
            return this.tomeOfBanishment;
        } else if (choice == 2) {
            return this.tomeOfWealth;
        } else if (choice == 3) {
            return this.tomeOfBlessing;
        }

        return 0;
    }

    /**
     * Adds requested tome to inventory. */
    public void addTome(int choice) {
        if (choice == 1) {
            this.tomeOfBanishment++;
        } else if (choice == 2) {
            this.tomeOfWealth++;
        } else if (choice == 3) {
            this.tomeOfBlessing++;
        }
    }

    /**
     * Uses the requested tome. */
    public void useTome(int choice) {
        if ((choice == 1) && (this.tomeOfBanishment > 0)) {
            this.tomeOfBanishment--;
        } else if ((choice == 2) && (this.tomeOfWealth > 0)) {
            this.tomeOfWealth--;
        } else if ((choice == 3) && (this.tomeOfBlessing > 0)) {
            this.tomeOfBlessing--;
        }
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Alt getAlt() {
        return alt;
    }

    public void setAlt(Alt alt) {
        this.alt = alt;
    }

    public ArrayList<Armor> getArmor() {
        return armor;
    }

    public void setArmor(Armor piece) {
        this.armor.add(piece);
    }

    public int getCoin() {
        return coin;
    }

    public void addCoin(int amount) {
        this.coin += amount;
    }

    public void spendCoin(int amount) {
        this.coin -= amount;
    }
}
