import javafx.scene.Scene;

public interface GameScene extends Scene{
    //Used to initialize the GameScene with
    //      any data it may need. This should
    //      be called immediatley after its created.
    void init(Object objs[]);

    //Used to setup anything that the GameScene
    //      may need to prepare each time it becomes
    //      active.
    void setup(Object objs[]);
}
