package com.dd.controller_util.controller;

import com.dd.command_util.command.network.NetworkHandledCommand;
import com.dd.network.NetworkCommChannel;
import com.dd.network.SocketCommChannel;
import com.dd.network.protocol.NetworkMessageInterpreter;
import com.dd.network.protocol.client.ClientMessageInterpreter;
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
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
		GameType gameType = args.getArgument("GameType");
		commandParser = new CommandParser(new CommandOutputLog(output));
		if(gameType == GameType.LOCAL) {
			commandParser.registerCommand("move", new MoveCommand(gameState));
			commandParser.registerCommand("examine", new ExamineCommand(gameState));
			commandParser.registerCommand("drop", new DropCommand(gameState));
			commandParser.registerCommand("attack", new AttackCommand(gameState));
			commandParser.registerCommand("equip", new EquipCommand(gameState));
			commandParser.registerCommand("help", new HelpCommand());
			commandParser.registerCommand("pickup", new PickupCommand(gameState));
			commandParser.registerCommand("use", new UseCommand());
		}
		else{
			Socket socket = args.getArgument("Socket");
			NetworkCommChannel commChannel = new SocketCommChannel(socket);
			NetworkMessageInterpreter commInterpreter = new ClientMessageInterpreter();
			NetworkHandledCommand netCommand = new NetworkHandledCommand(commChannel, commInterpreter, gameState);
			commandParser.registerCommand("move", netCommand);
			commandParser.registerCommand("examine", netCommand);
			commandParser.registerCommand("drop", netCommand);
			commandParser.registerCommand("attack", netCommand);
			commandParser.registerCommand("equip", netCommand);
			commandParser.registerCommand("help", netCommand);
			commandParser.registerCommand("pickup", netCommand);
			commandParser.registerCommand("use", netCommand);
			/*
			Spawn thread to handle comms from server
			*/
			/*
			if(gameType == GameType.NET_SERVER){
				//set the game type and get a reference to the server object
				//so it can be closed when the user quits the game
			}
			 */
		}
	}

	@Override
	public void teardown(){
		gameState = null;
		commandParser = null;
		/*
		if(gameServer != null)
			gameSever.kill();
		 */
	}
}