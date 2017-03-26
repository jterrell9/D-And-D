package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;

public class QuitCommand extends CommandHandler {
    public QuitCommand(){}

    @Override
<<<<<<< HEAD
    public void handleCommand(String[] args, CommandOutputLog outputLog){
=======
    public void handleCommand(String[] args){
>>>>>>> refs/remotes/origin/Testing
    	System.exit(0);
    }
}