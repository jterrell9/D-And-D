import javafx.scene.Scene;

//Defines a view within the game
//such as the start menu or the
//in game screen
public interface GameScene : Scene{
    GameSceneManager manager;
    void setup();
}

public interface GameSceneManager{
    setActiveGameScene(String name);
}
