public class CharacterInventory {
    private Weapon weapon;
    private Alt alt;
    private Armor[] armor;
    private int coin;

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

    public Armor[] getArmor() {
        return armor;
    }

    public void setArmor(Armor[] armor) {
        this.armor = armor;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
