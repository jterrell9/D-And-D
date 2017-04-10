package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.network.NetworkGameState;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientAuthenticationInstruction extends InstructionHandler {
    private ClientGameState gameState;

    public ClientAuthenticationInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
    }
}
