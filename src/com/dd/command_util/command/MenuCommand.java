package com.dd.command_util.command;

import java.io.FileNotFoundException;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class MenuCommand extends CommandHandler {
    public MenuCommand() {}

    @Override
    public void handleCommand(String[] args) throws FileNotFoundException{
    	Tester.mainMenu();
    }
}