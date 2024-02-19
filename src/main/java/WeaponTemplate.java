/**
 * WeaponTemplate.java interface that describes use of a weapon (attack)
 * The goal of this class is to create a weapon interface.
 * This implementation is based off the Factory pattern design pattern.
 * */

public interface WeaponTemplate {
    /**
     * set weapon name
     * @param name name of the weapon */
    void setName (String name);

    /**
     * set the additional attack
     * @param additionalAttack the additional attack damage a weapon does */
    void setAdditionalAttack (double additionalAttack);

    /**
     * get weapon name
     * @return name of the weapon */
    String getName();

    /**
     * get the base attack
     * @return base attack of the weapon */
    double getBaseAttack();

    /**
     * get the additional attack
     * @return additional attack of the weapon */
    double getAdditionalAttack();

    /**
     * get the full attack
     * @return full attack of the weapon */
    double getAttack();
}
