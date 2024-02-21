public class Enemy {
    private String name;
    private String attackName;
    private double attack;
    private double health;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttackName() {
        return attackName;
    }

    public void setAttackName(String attackName) {
        this.attackName = attackName;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void takeDamage(double amount) {
        if (amount >= this.health) {
            this.health = 0.0;
        } else {
            this.health -= amount;
        }
    }
}
