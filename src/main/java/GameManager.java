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
//        System.out.println(sender.getClass().getName() + " with event " + event);

        if (sender.getClass().equals(Character.class) && event.equals("player init")) {
            player_init = true;
        }

        if (sender.getClass().equals(Character.class) && event.equals("init health")) {
            Character player = (Character) sender;

            CharacterInfo info = player.getCharacterInfo();

            CharacterStats stats = player.getCharacterStats();


            double health = (
                PlayerConstants.BASE_HEALTH +
                        (stats.getDefense() * PlayerConstants.DEFENSE_BONUS) +
                        (stats.getLevel() * PlayerConstants.LEVEL_BONUS)
            );

            stats.setHealth(health);
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
