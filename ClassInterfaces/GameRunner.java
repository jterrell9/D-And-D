import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;

public class GameRunner extends Application implements GameSceneManager{
    //List of all GameStates found
    private static List<GameState> gameStateList;
    //Currently active GameState
    private static GameState activeGameState;
    //Map of all registered GameScenes
    private static Map<String, GameScene> gameSceneMap;
    //CommandParser to handle all necessary commands
    private static CommandParser commandParser;

    //Used to start the game
    public static void main(String[] args){
        //launch(args);
    }

    //Used to setup the various annonymous classes that
    //      will be the GameScenes. They should all be 
    //      appended to the gameSceneList, and the start 
    //      scene should be set as the scene of the primaryStage
    //pre: The JavaFX launch method has been called with
    //      the appropiate arguments
    //post: The game, as a JavaFX application, is setup
    @Override
    private static void start(Stage primaryStage){    
    }

    //Used to add a GameScene to the existing Map of
    //      GameScenes
    //pre: !gameSceneMap.keySet().contains(name)
    //      && !gameSceneMap.values().contains(gameState)
    //post: Key/Value pair name/gameState is added to
    //      gameSceneMap
    private static void addGameScene(String name, GameScene gameScene){
    }

    //Used to set activeGameScene
    //pre: gameScene.keySet().contains(name)
    //post: activeGameScene = gameScene.get(name)
    public static void setActiveGameScene(String name){   
    }

    //Used to get the list of GameStates that the game 
    //      was able to find
    public static List<GameState> getGameStateList(){
    }

    //Used to get the currently active GameState
    public static GameState getActiveGameState(){
    }

    //Use to set the currently active GameState
    //pre: gameStateList.contains(gState)
    //post: activeGameScene = gState
    public static void setActiveGameState(GameState gState){
    }

    //Used to serialize the activeGameState and save
    //it to a file
    //pre: activeGameState != null
    //post: activeGameState serialized and
    //      written to file
    public static void saveGame(){
    }

    //Used to load all serialized save files
    //      and put them into the gameStateList
    public static void loadSavedGames(){
    }

    //Used to join a non-local network game
    public static void joinGame(GameState gState){
    }
}
