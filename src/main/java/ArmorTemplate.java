/**
 * ArmorTemplate.java interface that describes parts of an armor piece
 * The goal of this class is to create an armor piece interface.
 * This implementation is based off the Factory pattern design pattern.
 * */

public interface ArmorTemplate {
    /**
     * set armor name
     * @param name name of the armor piece
     */
    void setName(String name);

    /**
     * get weapon name
     * @return name of the weapon
     */
    String getName();

    /**
     * set the additional attack
     * @param additionalAttack the additional attack damage a weapon does
     */
    void setResistance(double additionalAttack);

    /**
     * get the base attack
     * @return base attack of the weapon
     */
    double getResistance();
}
