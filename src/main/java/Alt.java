/**
 * Alt.java implementation of an Alt class using the WeaponTemplate interface
 * */

public class Alt extends Weapon {
    private double additionalAttack = WeaponConstants.ALT_ADDITIONAL_ATTACK;
    private int cooldown = 0;

    /**
     * Returns the current cooldown.
     * @return cooldown */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Decreases the current cooldown. */
    public void decreaseCooldown() {
        if (this.cooldown != 0) {
            this.cooldown -= 1;
        }
    }

    public void setAdditionalAttack(double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    public double getBaseAttack() {
        return WeaponConstants.ALT_BASE_ATTACK;
    }

    public double getAdditionalAttack() {
        return additionalAttack;
    }

    /**
     * Gets the attack power.
     * @return attack power */
    public double getAttack() {
        cooldown = WeaponConstants.ALT_COOLDOWN;

        if (Game.getPlayer() == null) {
            return WeaponConstants.ALT_BASE_ATTACK;
        }

        return (
                WeaponConstants.ALT_ADDITIONAL_ATTACK
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
