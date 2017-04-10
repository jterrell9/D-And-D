package com.dd.network.protocol.server;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;

public class ServerQuitInstruction extends InstructionHandler{
    private ServerGameState gameState;

    public ServerQuitInstruction(ServerGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
    }
}
