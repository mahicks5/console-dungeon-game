public abstract class Weapon implements WeaponTemplate {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void setAdditionalAttack(double additionalAttack);

    public abstract double getBaseAttack();

    public abstract double getAdditionalAttack();

    public abstract double getAttack();
}
