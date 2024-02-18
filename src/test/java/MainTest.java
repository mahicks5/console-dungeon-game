import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    WeaponFactory weaponFactory;

    @Before
    public void setUp() throws Exception {
        weaponFactory = new WeaponFactory();
    }

    @Test
    public void testWeaponFactoryWeaponTypes() {
        Weapon swordTest = weaponFactory.makeWeapon("Sword");
        swordTest.setName("Apothican Sword");
        swordTest.setAdditionalAttack(10);

        Weapon staffTest = weaponFactory.makeWeapon("Staff");
        staffTest.setName("Staff of Ice");
        staffTest.setAdditionalAttack(10);


        Weapon bowTest = weaponFactory.makeWeapon("Bow");
        bowTest.setName("Wrath of the Ancients");
        bowTest.setAdditionalAttack(10);

        assertEquals(Sword.class, swordTest.getClass());
        assertEquals(Staff.class, staffTest.getClass());
        assertEquals(Bow.class, bowTest.getClass());

        assertEquals(swordTest.getName(), "Apothican Sword");
        assertEquals(staffTest.getName(), "Staff of Ice");
        assertEquals(bowTest.getName(), "Wrath of the Ancients");

        assertEquals(swordTest.getAttack(), 13.0, 0.0);
        assertEquals(staffTest.getAttack(), 14.0, 0.0);
        assertEquals(bowTest.getAttack(), 12.0, 0.0);
    }
}
