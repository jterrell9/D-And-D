package com.dd.command_util.command.network;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.network.NetworkCommChannel;
import com.dd.network.protocol.NetworkMessageInterpreter;

public class NetworkHandledCommand extends CommandHandler{
    private NetworkCommChannel channel;
    private NetworkMessageInterpreter interpreter;

    public NetworkHandledCommand(NetworkCommChannel channel, NetworkCommInterpreter interpreter, GameState gameState){
        super(gameState);
        this.channel = channel;
        this.interpreter = interpreter;
    }

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog){
    	if(dead){
    		outputLog.printToLog(player.titleToString() + " is dead. ");
    		return;
    	}
    	setGlobalOutputLog(outputLog);
    	updateState();
    }
}
