package com.dd.command_util.command;

import java.io.FileNotFoundException;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.tester.Tester;

public class MenuCommand extends CommandHandler {
    public MenuCommand() {}

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog) throws FileNotFoundException{
    	Tester.mainMenu();
    }
}