package com.dd.controller_util.controller;


import com.dd.DandD;
import com.dd.GameState;
import com.dd.controller_util.ControllerArgumentPackage;
import com.dd.controller_util.GameSceneController;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LoadGameController extends GameSceneController{
	
	@FXML ListView savefiles;
	@FXML Button loadButton;
	@FXML Button backButton;
	
	/**
	 * Event handler for "Load Game" button.
	 */
	@FXML
	private void handleLoadButtonAction(ActionEvent event) throws IOException {
		//NEED TO REWORK THIS TO BE AUTOMATIC
		/*
		Scanner scanName = new Scanner(System.in);
		System.out.print("Enter Player's Name: ");
		String name = scanName.nextLine();
		File gameFile = new File(name+".json");
		if(gameFile.exists()){
			Scanner scanJsonFile = new Scanner(gameFile);
			if(scanJsonFile.hasNextLine()){
				String JsonString = scanJsonFile.nextLine();
				GameState loadedGame = new Gson().fromJson(JsonString, GameState.class);
				DandD.setActiveGameScene("RunningGameScene", new Object[]{loadedGame});
			}else{
				System.out.println("ERROR Json file is empty!");
				return;
			}
		}
		else {
			System.out.println("ERROR file does not exit");
			return;
		}
		*/
	}
	
	/**
	 * Event handler for "Back" button.
	 */
	@FXML
	private void handleBackButtonAction(ActionEvent event) throws IOException {
		DandD.setActiveGameScene("MainMenuScene", null);
	}
	
	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {
		
	}

	@Override
	public void setup(ControllerArgumentPackage args){

	}

	@Override
	public void teardown(){

	}
}
