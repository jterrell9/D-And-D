package com.dd.network.protocol.server;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;

public class ServerMoveInstruction extends InstructionHandler{
    private ServerGameState gameState;

    public ServerMoveInstruction(ServerGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
    }
}
