import javafx.scene.Scene;

//Defines a view within the game
//such as the start menu or the
//in game screen
public interface GameScene : Scene{
    GameSceneManager manager;
    
    //Called immediately after the
    //GameScene is created
    void init(Object objs[]);

    //Called whenever the GameScene
    //becomes active
    void setup(Object objs[]);
}

public interface GameSceneManager{
    setActiveGameScene(String name);
}
