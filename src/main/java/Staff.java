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

    /**
     * Gets the attack power of the weapon.
     * @return attack power */
    public double getAttack() {
        if (Game.getPlayer() == null) {
            return WeaponConstants.STAFF_BASE_ATTACK;
        }

        return (
                WeaponConstants.STAFF_BASE_ATTACK
                        + (
                                Game.getPlayer().getCharacterStats().getAttack()
                                        * WeaponConstants.ATTACK_STAT_BONUS
                )
                        + (
                                Game.getPlayer().getCharacterStats().getLevel()
                                        * additionalAttack
                )
            );
    }
}
