package com.dd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

public class RunningGameController {
	@FXML private TextField input;
	@FXML private TextArea output;
	@FXML private TextArea map;
	@FXML private TextArea stats;
	@FXML private Button saveGame;
	@FXML private Button exitGame;
	
	/**
	 * Event handler for "Enter" key.
	 */
	@FXML
	private void handleEnterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			System.out.println("Enter key pressed!");
	    }
	}
	
	/**
	 * Event handler for "Save" button.
	 */
	@FXML
	private void saveGameAction(ActionEvent event) throws IOException {
		System.out.println("Save game clicked!");
	}
	
	/**
	 * Event handler for "Exit" button.
	 */
	@FXML
	private void exitGameAction(ActionEvent event) throws IOException {
		Parent mainMenu = FXMLLoader.load(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
		Scene scene = new Scene(mainMenu);
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}
}