import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;

public class GameRunner extends Application implements GameSceneManager{
    private List<GameState> gameStateList;
    private GameState activeGameState;
    private Map<String, GameScene> gameSceneMap;
    private CommandParser commandParser;

    public GameRunner(){
    
    }

    //Used to load save states and settings as
    //well as a wrapper for the JavaFX launch method
    public void runGame(String[] args){
        //launch(args);
    }

    //Used to setup the various annonymous classes that
    //will be the GameScenes. They should all be appended
    //to the gameSceneList, and the start scene should be
    //set as the scene of the primaryStage
    @Override
    private void start(Stage primaryStage){
    
    }

    //This will change the currently active scene
    //in JavaFX. Before doing so however it will
    //verify that the named scene exists and then
    //it will call the respective GameScenes setup
    //method
    private void setActiveGameScene(String name){
    
    }

    //This will add a GameScene to the exisitng Map
    //of GameScenes
    private void addGameScene(String name, GameScene, gameScene){
    
    }
}
