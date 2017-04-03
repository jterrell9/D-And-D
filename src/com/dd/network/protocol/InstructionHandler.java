package com.dd.network.protocol;

import com.dd.network.NetworkGameState;

import java.nio.ByteBuffer;

public abstract class InstructionHandler {
    public abstract void handleInstruction(ByteBuffer instruction, NetworkGameState gameState);
}
