/**
 * CharacterStats.java class that holds the character's stats
 * */
public class CharacterStats {
    private int level;
    private int xp;
    private double health;
    private int attack;
    private int defense;
    private int stamina;
    private int speed;
    private String status;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void increaseAttack() {
        this.attack += 1;
    }

    public void increaseDefense() {
        this.defense += 1;
    }

    public void increaseStamina() {
        this.stamina += 1;
    }

    public void increaseSpeed() {
        this.speed += 1;
    }
}
