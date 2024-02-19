/**
 * Armor.java interface that describes parts of an armor piece
 * The goal of this class is to create an armor piece interface.
 * This implementation is based off the Factory pattern design pattern.
 * */

public interface Armor {
    /**
     * set weapon name
     * @param name name of the armor piece */
    void setName (String name);

    /**
     * get weapon name
     * @return name of the weapon */
    String getName();

    /**
     * get the base attack
     * @return base attack of the weapon */
    double getBaseAttack();
}
