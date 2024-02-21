/**
 * Staff.java implementation of a Bow using the WeaponTemplate interface
 * */

public class Staff extends Weapon {
    private double additionalAttack;

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getBaseAttack() {
        return WeaponConstants.STAFF_BASE_ATTACK;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    public double getAttack() {
        return WeaponConstants.STAFF_BASE_ATTACK + (Game.getPlayer().getCharacterStats().getLevel() * additionalAttack);
    }
}
