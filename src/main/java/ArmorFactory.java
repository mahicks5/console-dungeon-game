/**
 * ArmorFactory.java used to create armor of a certain type.
 * */

public class ArmorFactory {
    /**
     * creates a new piece of head armor.
     * @return HeadArmor armor in the class of helmets */
    public HeadArmor makeHeadArmor() {
        return new HeadArmor();
    }

    /**
     * creates a new piece of chest armor.
     * @return ChestArmor armor in the class of chestpiece */
    public ChestArmor makeChestArmor() {
        return new ChestArmor();
    }

    /**
     * creates a new piece of arm armor.
     * @return ArmArmor armor in the class of arms or gauntlets */
    public ArmArmor makeArmsArmor() {
        return new ArmArmor();
    }

    /**
     * creates a new piece of leg armor.
     * @return LegArmor armor in the class of legs */
    public LegArmor makeLegArmor() {
        return new LegArmor();
    }

    /**
     * creates a new piece of boot armor.
     * @return BootArmor armor in the class of boots */
    public BootArmor makeBootArmor() {
        return new BootArmor();
    }
}
