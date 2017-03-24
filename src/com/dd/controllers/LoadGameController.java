package com.dd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadGameController {
	
	@FXML ListView savefiles;
	@FXML Button loadGame;
	@FXML Button back;
	
	/**
	 * Event handler for "Load Game" button.
	 */
	@FXML
	private void loadGameAction(ActionEvent event) throws IOException {		
		Parent runningGame = FXMLLoader.load(getClass().getResource("/com/dd/fxml/RunningGame.fxml"));
		Scene scene = new Scene(runningGame);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Event handler for "Back" button.
	 */
	@FXML
	private void backAction(ActionEvent event) throws IOException {
		Parent runningGame = FXMLLoader.load(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
		Scene scene = new Scene(runningGame);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}
	
	

}
