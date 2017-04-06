package com.dd.controller_util.controller;


import com.dd.DandD;
import com.dd.GameState;
import com.dd.controller_util.ControllerArgumentPackage;
import com.dd.controller_util.GameSceneController;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadGameController extends GameSceneController{
	
	@FXML private ListView<String> fileList;
	@FXML private Button loadButton;
	@FXML private Button backButton;
	@FXML private Label errorLabel;
	
	private List<GameState> gamestates;
	
	/**
	 * Event handler for "Load Game" button.
	 */
	@FXML
	private void handleLoadButtonAction(ActionEvent event) {
		int selectedIndex = fileList.getSelectionModel().getSelectedIndex();
		
		if (selectedIndex < 0) {
			errorLabel.setText("Please select a file.");
			return;
		}
		
		GameState game = gamestates.get(selectedIndex);
		
		ControllerArgumentPackage args = new ControllerArgumentPackage();
		args.setArgument("GameState", game);

		DandD.setActiveGameScene("RunningGameScene", args);
	}
	
	/**
	 * Adds the list of GameStates to the ListView.
	 */
	private void displayGameStates() {
		
		// Get the folder path to the working directory
		File folder = new File(System.getProperty("user.dir"));
		
		// Get all of the .json files
		File[] files = folder.listFiles(new FilenameFilter() {
			public boolean accept(File folder, String name) {
				return name.toLowerCase().endsWith(".json");
			}
		});
		
		// Load up all the save files and stores them in the GameState ArrayList
		this.gamestates = new ArrayList<>();
		for (File file : files) {
			
			try {
				FileReader filereader = new FileReader(file);
				gamestates.add(new Gson().fromJson(filereader, GameState.class));
			}
			catch (FileNotFoundException e) {
				errorLabel.setText("Error opening a file.");
				return;
			}
		}
		
		// Now populate the ListView
		for (GameState gamestate : gamestates) {
			String description = gamestate.getName() + " - "
							   + "Player: " + gamestate.getActivePlayer().getName();
			
			fileList.getItems().add(description);
		}
	}
	
	/**
	 * Event handler for "Back" button.
	 */
	@FXML
	private void handleBackButtonAction(ActionEvent event) {
		DandD.setActiveGameScene("MainMenuScene", null);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}

	@Override
	public void setup(ControllerArgumentPackage args){
		gamestates = null;
		fileList.getItems().clear();
		errorLabel.setText("");
		displayGameStates();
	}

	@Override
	public void teardown(){

	}
}
