/**
 * GameManager.java
 * Acts as the controlling mechanism for the game. All classes report to this and
 * all events are ran through this.
 * This used the mediator pattern for communication. This class also uses the singleton
 * design pattern to ensure that it is the only GameManager.*/

public class GameManager implements GameMediator {
    GameManager manager;
    boolean player_init = false;

    public GameManager() { }

    @Override
    public void alert(Object sender, String event) {
        // notifies game manager when the player is initialized
        if (sender.getClass().equals(Character.class) && event.equals("player init")) {
            player_init = true;
        }

        // notifies game manager to initialize player health
        if (sender.getClass().equals(Character.class) && event.equals("init health")) {
            Character player = (Character) sender;

            CharacterStats stats = player.getCharacterStats();


            double health = (
                PlayerConstants.BASE_HEALTH +
                        (stats.getDefense() * PlayerConstants.DEFENSE_BONUS) +
                        (stats.getLevel() * PlayerConstants.LEVEL_BONUS)
            );

            stats.setHealth(health);
        }

        if (sender.getClass().equals(Character.class) && event.equals("enter dungeon")) {
            DungeonManager.displayDanger();
            DungeonManager.displayDungeonTitle();
            DungeonManager.dungeonLoop();
        }

        if (sender.getClass().equals(Character.class) && event.equals("leave dungeon")) {
            // TODO leave
        }
    }

    @Override
    public GameManager getGameManager() {
        if (manager == null) {
            manager = new GameManager();
        }

        return manager;
    }
}
