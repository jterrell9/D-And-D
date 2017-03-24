package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;

public class QuitCommand extends CommandHandler {
    public QuitCommand(){}

    @Override
    public void handleCommand(String[] args){
    	System.exit(0);
    }
}