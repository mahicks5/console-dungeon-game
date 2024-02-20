/**
 * Sword.java implementation of a Sword using the WeaponTemplate interface
 * */

public class Sword extends Weapon {
    private double additionalAttack;

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getBaseAttack() {
        return WeaponConstants.SWORD_BASE_ATTACK;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    public double getAttack() {
        return WeaponConstants.SWORD_BASE_ATTACK + additionalAttack;
    }
}
