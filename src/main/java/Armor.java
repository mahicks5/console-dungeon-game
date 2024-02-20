public abstract class Armor implements ArmorTemplate {
    private String name;
    private double resistance;

    /**
     * set armor name
     * @param name name of the armor piece
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get weapon name
     * @return name of the armor piece
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set the additional attack
     * @param resistance the resistance of an armor piece
     */
    @Override
    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    /**
     * get the base attack
     * @return base attack of the weapon
     */
    @Override
    public double getResistance() {
        return resistance;
    }
}
