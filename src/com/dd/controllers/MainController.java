package com.dd.controllers;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController extends Application {
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
		Scene scene = new Scene(loader.load());
        
        primaryStage.setTitle("D&D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}