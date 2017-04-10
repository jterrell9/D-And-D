package com.dd.network.protocol.server;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.network.ClientGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;

public class ServerAttackInstruction extends InstructionHandler{
    private ServerGameState gameState;

    public ServerAttackInstruction(ServerGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
    }
}
