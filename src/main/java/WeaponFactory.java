/**
 * WeaponFactory.java used to create a weapons of a certain type
 * */

import java.util.Random;

public class WeaponFactory {
    /**
     * */
    public Weapon makeWeapon(String weaponType) {
        if (weaponType.isEmpty()) {
            return null;
        }

        if (weaponType.equals("Sword")) {
            return new Sword();
        }

        if (weaponType.equals("Staff")) {
            return new Staff();
        }

        if (weaponType.equals("Bow")) {
            return new Bow();
        }

        return null;
    }

    public Weapon makeRandomWeapon() {
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;

        if (randomNumber == 1) {
            return makeWeapon("Sword");
        } else if (randomNumber == 2) {
            return makeWeapon("Staff");
        } else if (randomNumber == 3) {
            return makeWeapon("Bow");
        }

        return null;
    }
}
