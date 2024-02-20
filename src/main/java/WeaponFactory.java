/**
 * WeaponFactory.java used to create a weapons of a certain type
 * */

public class WeaponFactory {
    /**
     * */
    public Sword makeSword() {
        return new Sword();
    }

    public Staff makeStaff() {
        return new Staff();
    }

    public Bow makeBow() {
            return new Bow();
    }
}
