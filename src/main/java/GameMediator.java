/**
 * GameMediator.java interface that outlines some basic functionally of GameMediator class
 * */

public interface GameMediator {
    void alert(Object sender, String event);

    GameManager getGameManager();
}
