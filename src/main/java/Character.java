public class Character {
    private CharacterInfo characterInfo;
    private CharacterStats characterStats;
    private CharacterInventory characterInventory;

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
