import java.util.ArrayList;

/**
 * CharacterInventory.java class that holds the character inventory
 *
 * */

public class CharacterInventory {
    private Weapon weapon;
    private Alt alt;
    private final ArrayList<Armor> armor = new ArrayList<>();
    private int coin;

    public void useHealthPotion() {
        if (this.healthPotions > 0) {
            this.healthPotions -= 1;
        }
    }

    public void addHealthPotion() {
        this.healthPotions += 1;
    }

    public int checkPotions() {
        return this.healthPotions;
    }

    private int healthPotions;

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

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
