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
		Parent newGame = FXMLLoader.load(getClass().getResource("/com/dd/fxml/NewGame.fxml"));
		Scene scene = new Scene(newGame);
		
		// Get the stage and set the scene to it
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
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
	private void loadGameAction(ActionEvent event) throws IOException {
		Parent loadGame = FXMLLoader.load(getClass().getResource("/com/dd/fxml/LoadGame.fxml"));
		Scene scene = new Scene(loadGame);
		
		// Get the stage and set the scene to it
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}
	
}