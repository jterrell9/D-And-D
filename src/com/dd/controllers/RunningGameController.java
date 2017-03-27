package com.dd.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javafx.application.Platform;
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
	@FXML private Button saveButton;
	@FXML private Button exitButton;
	
	private String seedNumber;
	private String characterName;
	private String characterClass;
	
	/**
	 * Event handler for "Enter" key.
	 */
	@FXML
	private void handleEnterPressed(KeyEvent event) {
		
		// if enter pressed and input is not empty
		if (event.getCode() == KeyCode.ENTER && !input.getText().equals("")) {
			
			// append to output and clear the textfield
			output.appendText(input.getText() + "\n");
			input.clear();
	    }
	}
	
	/**
	 * Event handler for "Save" button.
	 */
	@FXML
	private void handleSaveButtonAction(ActionEvent event) throws IOException {
		System.out.println("Save game clicked!");
	}
	
	/**
	 * Event handler for "Exit" button.
	 */
	@FXML
	private void handleExitButtonAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/MainMenu.fxml"));
		Scene scene = new Scene(loader.load());
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Appends text to the TextArea output.
	 */
	public void appendText(String str) {
		Platform.runLater(() -> output.appendText(str));
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
		// Redirect System.out to TextArea
		OutputStream out = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				appendText(String.valueOf((char) b));
			}
		};
		System.setOut(new PrintStream(out, true));
	}
	
	public void setSeedNumber(String seed) {
		this.seedNumber = seed;
	}
	
	public void setCharacterName(String name) {
		this.characterName = name;
	}
	
	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}
}