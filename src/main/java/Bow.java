/**
 * Bow.java implementation of a Bow using the Weapon interface
 * */

public class Bow implements Weapon {
    private String name;
    private final double BASEATTACK = 2.0;
    private double additionalAttack;
    @Override
    public double attack() {
        return BASEATTACK + additionalAttack;
    }

    @Override
    public void setName (String name) {
        this.name = name;
    }

    @Override
    public void setAdditionalAttack (double additionalAttack) {
        this.additionalAttack = additionalAttack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBaseAttack() {
        return BASEATTACK;
    }

    @Override
    public double getAdditionalAttack() {
        return additionalAttack;
    }

    @Override
    public double getAttack() {
        return BASEATTACK + additionalAttack;
    }
}
