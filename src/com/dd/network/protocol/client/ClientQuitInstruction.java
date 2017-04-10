package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientQuitInstruction extends InstructionHandler {
    private ClientGameState gameState;

    public ClientQuitInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
    }
}
