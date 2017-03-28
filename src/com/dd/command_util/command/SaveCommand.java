package com.dd.command_util.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.dd.Console;
import com.dd.DandD;
import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.google.gson.Gson;

public class SaveCommand extends CommandHandler {
    public SaveCommand(){}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog) throws FileNotFoundException {
    	File gsonFile = new File(player().getName()+".json");
		PrintStream toGsonFile = new PrintStream(gsonFile);
		toGsonFile.println(new Gson().toJson(DandD.getActiveGameState()));
		toGsonFile.close();
		Console.updateScreen(player().getName() + " has saved the Game.");
    }
}