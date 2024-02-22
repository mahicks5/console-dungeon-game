/**
 * WeaponFactory.java used to create a weapons of a certain type.
 * */

public class WeaponFactory {
    /**
     * Makes a new sword.
     * @return sword */
    public Sword makeSword() {
        return new Sword();
    }

    /**
     * Makes a new staff.
     * @return staff */
    public Staff makeStaff() {
        return new Staff();
    }

    /**
     * Makes a new bow.
     * @return bow */
    public Bow makeBow() {
        return new Bow();
    }
}
