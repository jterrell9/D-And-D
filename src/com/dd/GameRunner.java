package com.dd;

import com.dd.GameState;
import com.dd.gamescene_util.GameScene;
import com.dd.gamescene_util.gamescene.*;

import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameRunner extends Application {
    private static Stage stage;
    private static List<GameState> gameStateList = new ArrayList<GameState>();
    private static GameState activeGameState;
    private static Map<String, GameScene> gameSceneMap = new HashMap<String, GameScene>();
    private static int screenWidth;
    private static int screenHeight;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
            Scene scene = new Scene(loader.load());
            
            primaryStage.setTitle("D&D");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
            System.exit(2);
        }
        
        /*
        screenWidth = 1920;
        screenHeight = 1080;        

        GameScene mainMenuScene =  new MainMenuScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene newGameScene = new NewGameScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene loadGameScene = new LoadGameScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene joinGameScene = new JoinGameScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene addServerScene = new AddServerScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene characterCreationScene = new CharacterCreationScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);
        GameScene runningGameScene = new RunningGameScene(new StackPane(),
                                                        screenWidth,
                                                        screenHeight);

        addGameScene("MainMenuScene", mainMenuScene);
        addGameScene("NewGameScene", newGameScene);
        addGameScene("LoadGameScene", loadGameScene);
        addGameScene("JoinGameScene", joinGameScene);
        addGameScene("AddServerScene", addServerScene);
        addGameScene("CharacterCreationScene", characterCreationScene);
        addGameScene("RunningGameScene", runningGameScene);

        setActiveGameScene("MainMenuScene", null);
        
        primaryStage.show();
        */
    }

    private static void addGameScene(String name, GameScene gameScene) {
        if(gameSceneMap.containsKey(name))
            throw new IllegalArgumentException("GameScene \""
                                                + name
                                                + "\" is already registered with the GameRunner. Registration failed.");
        gameSceneMap.put(name, gameScene);
    }

    public static void setActiveGameScene(String name, Object[] args) {
        GameScene gameScene = gameSceneMap.get(name);
        if(gameScene == null)
            throw new IllegalArgumentException("GameScene \""
                                                + name
                                                + "\" is not registered with the GameRunner. GameScene unchanged.");
        gameScene.setup(args);
        stage.setScene(gameScene);
    }

    public static void registerGameState(GameState gameState) {
        gameStateList.add(gameState);
    }

    public static GameState getActiveGameState() {
        return activeGameState;
    }

    public static void setActiveGameState(GameState gameState) {
        if(!gameStateList.contains(gameState))
            throw new IllegalArgumentException("GameState \""
                                                + gameState.getName()
                                                + "\"  is not registered with the GameRunner. Activation failed.");
        activeGameState = gameState;
    }

    public static List<GameState> getGameStateList() {
        return gameStateList;
    }

    public static void saveGame() {
    }

    public static void loadSavedGames() {
    }
}