/**
 * Bow.java implementation of a Bow using the WeaponTemplate interface
 * */

public class Bow extends Weapon {
    private String name;
    private double additionalAttack;

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getBaseAttack() {
        return WeaponConstants.BOW_BASE_ATTACK;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    public double getAttack() {
        return WeaponConstants.BOW_BASE_ATTACK + additionalAttack;
    }
}
