package com.dd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	@FXML private Button newGame;
	@FXML private Button joinGame;
	@FXML private Button loadGame;
	
	/**
	 * Event handler for "New Game" button.
	 */
	@FXML
	private void newGameAction(ActionEvent event) throws IOException {		
		Parent runningGame = FXMLLoader.load(getClass().getResource("/com/dd/fxml/NewGame.fxml"));
		Scene scene = new Scene(runningGame);
		
		// This is one way to get the stage and set a scene to it
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		
		// Another way is to make it public and do it directly
		//MainController.stage.setScene(scene);
	}
	
	/**
	 * Event handler for "Join Game" button.
	 */
	@FXML
	private void joinGameAction(ActionEvent event) {
		System.out.println("Clicked join game!");
	}
	
	/**
	 * Event handler for "Load Game" button.
	 */
	@FXML
	private void loadGameAction(ActionEvent event) {
		System.out.println("Clicked load game!");
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}
	
}