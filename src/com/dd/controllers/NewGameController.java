package com.dd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewGameController {
	@FXML TextField seedNumber;
	@FXML TextField characterName;
	@FXML Button startGame;
	@FXML Button back;
	
	/**
	 * Event handler for "Start Game" button.
	 */
	@FXML
	private void startGameAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/RunningGame.fxml"));
		Scene scene = new Scene(loader.load());
		
		RunningGameController controller = loader.getController();
		controller.setSeed(seedNumber.getText());
		controller.setName(characterName.getText());
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Event handler for "Back" button.
	 */
	@FXML
	private void backAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
		Scene scene = new Scene(loader.load());
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}
}
