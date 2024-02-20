/**
 * Alt.java implementation of an Alt class using the WeaponTemplate interface
 * */

public class Alt extends Weapon {
    private String name;
    private double additionalAttack = WeaponConstants.ALT_ADDITIONAL_ATTACK;

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getBaseAttack() {
        return WeaponConstants.ALT_BASE_ATTACK;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    public double getAttack() {
        return WeaponConstants.ALT_BASE_ATTACK + additionalAttack;
    }
}
