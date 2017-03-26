package com.dd.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NewGameController {
	@FXML TextField seedNumber;
	@FXML TextField characterName;
	@FXML Button startButton;
	@FXML Button backButton;
	@FXML RadioButton fighterRadio;
	@FXML RadioButton wizardRadio;
	@FXML ToggleGroup characterClass;
	@FXML Label errorLabel;
	
	/**
	 * Checks the form to make sure nothing is empty or invalid.
	 */
	private boolean checkFields() {
		
		// check for empty fields
		if (seedNumber.getText().equals("")) {
			errorLabel.setText("Please enter a seed number.");
			return false;
		}
		else if (characterName.getText().equals("")) {
			errorLabel.setText("Please enter a character name.");
			return false;
		}
		else if (characterClass.getSelectedToggle() == null) {
			errorLabel.setText("Please select a character class.");
			return false;
		}
		
		// make sure the seed number is an integer
		try {
			Integer.parseInt(seedNumber.getText());
		}
		catch (NumberFormatException e) {
			errorLabel.setText("Please enter a valid seed number.");
			return false;
		}	
		
		return true;
	}
	
	/**
	 * Returns a string representation of the selected radio button.
	 */
	private String getRadioButtonText() {
		if (characterClass.getSelectedToggle() == null) {
			return "";
		}
		
		RadioButton rbutton = (RadioButton) characterClass.getSelectedToggle();
		return rbutton.getText();
	}
	
	/**
	 * Event handler for "Start Game" button.
	 */
	@FXML
	private void handleStartButtonAction(ActionEvent event) throws IOException {
		if (!checkFields()) {
			return;
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dd/fxml/RunningGame.fxml"));
		Scene scene = new Scene(loader.load());
		
		RunningGameController controller = loader.getController();
		controller.setSeedNumber(seedNumber.getText());
		controller.setCharacterName(characterName.getText());
		controller.setCharacterClass(getRadioButtonText());
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
	}
	
	/**
	 * Event handler for "Back" button.
	 */
	@FXML
	private void handleBackButtonAction(ActionEvent event) throws IOException {
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
