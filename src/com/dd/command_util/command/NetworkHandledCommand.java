package com.dd.command_util.command;

import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.network.NetworkCommChannel;
import com.dd.network.NetworkCommInerpreter;

public class NetworkHandledCommand extends CommandHandler{
    private NetworkCommChannel channel;
    private NetworkCommInerpreter interpreter;

    public NetworkHandledCommand(NetworkCommChannel channel, NetworkCommInerpreter interpreter){
        this.channel = channel;
        this.interpreter = interpreter;
    }

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog){

    }
}
