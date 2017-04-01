package com.dd.controller_util.controller;

import com.google.gson.Gson;
import com.dd.DandD;
import com.dd.GameState;
import com.dd.GameType;
import com.dd.command_util.CommandOutputLog;
import com.dd.command_util.CommandParser;
import com.dd.command_util.command.*;
import com.dd.controller_util.ControllerArgumentPackage;
import com.dd.controller_util.GameSceneController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

public class RunningGameController extends GameSceneController{
	@FXML private TextField input;
	@FXML private TextArea output;
	@FXML private TextArea map;
	@FXML private TextArea stats;
	@FXML private Button saveButton;
	@FXML private Button exitButton;

	private GameState gameState;
	private CommandParser commandParser;
	
	/**
	 * Event handler for "Enter" key.
	 */
	@FXML
	private void handleEnterPressed(KeyEvent event) {
		
		// if enter pressed and input is not empty
		if (event.getCode() == KeyCode.ENTER && !input.getText().equals("")) {
			// append to output and clear the textfield
			String commandStr = input.getText();
			output.appendText(input.getText() + "\n");
			input.clear();
			try{
				commandParser.parse(commandStr);
			}
			catch(CommandParser.InvalidCommandException e){
				output.appendText("The command string \""
									+ commandStr
									+ "\" is invalid. Type \"help\" for a list of commands.\n");
			}
			catch(FileNotFoundException e){
				output.appendText("ERROR: File issue!\n");
			}
	    }
	}
	
	/**
	 * Event handler for "Save" button.
	 */
	@FXML
	private void handleSaveButtonAction(ActionEvent event) throws IOException {
		File gsonFile = new File(gameState.getActivePlayer().getName()+".json");
		PrintStream toGsonFile = new PrintStream(gsonFile);
		toGsonFile.println(new Gson().toJson(gameState));
		toGsonFile.close();
	}
	
	/**
	 * Event handler for "Exit" button.
	 */
	@FXML
	private void handleExitButtonAction(ActionEvent event) throws IOException {
		DandD.setActiveGameScene("MainMenuScene", null);
	}
	
	/**
	 * Appends text to the TextArea output.
	 */
	public void appendText(String str) {
		Platform.runLater(() -> output.appendText(str));
	}

	public void setGameState(GameState gameState){
		this.gameState = gameState;
	}

	/**
	 * Called when fxml document is loaded.
	 */
	public void initialize() {

	}

	@Override
	public void setup(ControllerArgumentPackage args){
		GameState gameState = args.getArgument("GameState");
		this.gameState = gameState;
		commandParser = new CommandParser(new CommandOutputLog(output));
		commandParser.registerCommand("move", new MoveCommand(gameState));
		commandParser.registerCommand("examine", new ExamineCommand(gameState));
		commandParser.registerCommand("drop", new DropCommand(gameState));
		commandParser.registerCommand("attack", new AttackCommand(gameState));
		commandParser.registerCommand("equip", new EquipCommand(gameState));
		commandParser.registerCommand("help", new HelpCommand());
		commandParser.registerCommand("pickup", new PickupCommand(gameState));
		commandParser.registerCommand("use", new UseCommand());
	}

	@Override
	public void teardown(){
		gameState = null;
		commandParser = null;
	}
}