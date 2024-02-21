/**
 * CharacterInventory.java class that holds the character inventory
 * */

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
    private int tomeOfExperience = 0;

    public int checkHealthPotions() {
        return this.healthPotions;
    }

    public void addHealthPotion() {
        this.healthPotions += 1;
    }

    public void useHealthPotion() {
        if (this.healthPotions > 0) {
            this.healthPotions -= 1;
        }
    }

    public int checkAntidotes() {
        return this.antidotes;
    }

    public void addAntidotesPotion() {
        this.antidotes += 1;
    }

    public void useAntidotesPotion() {
        if (this.antidotes > 0) {
            this.antidotes -= 1;
        }
    }

    public int checkTome(int choice) {
        if (choice == 1) {
            return this.tomeOfBanishment;
        } else if (choice == 2) {
            return this.tomeOfWealth;
        } else if (choice == 3) {
            return this.tomeOfExperience;
        }

        return 0;
    }

    public void addTome(int choice) {
        if (choice == 1) {
            this.tomeOfBanishment++;
        } else if (choice == 2) {
            this.tomeOfWealth++;
        } else if (choice == 3) {
            this.tomeOfExperience++;
        }
    }

    public void useTome(int choice) {
        if ((choice == 1) && (this.tomeOfBanishment > 0)) {
            this.tomeOfBanishment--;
        } else if ((choice == 2) && (this.tomeOfWealth > 0)) {
            this.tomeOfWealth--;
        } else if ((choice == 3) && (this.tomeOfExperience > 0)) {
            this.tomeOfExperience--;
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
