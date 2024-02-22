/**
 * Character.java interface that describes parts of an armor piece.
 * The goal of this class is to allow creation of player characters
 * This implementation is based off the Strategy pattern design pattern.
 * */

public class Character {
    private final CharacterInfo characterInfo;
    private final CharacterStats characterStats;
    private final CharacterInventory characterInventory;

    /**
     * Constructor for Character class. */
    public Character() {
        characterInfo = new CharacterInfo();
        characterStats = new CharacterStats();
        characterInventory = new CharacterInventory();
    }

    public CharacterInfo getCharacterInfo() {
        return characterInfo;
    }

    public CharacterStats getCharacterStats() {
        return characterStats;
    }

    public CharacterInventory getCharacterInventory() {
        return characterInventory;
    }
}
