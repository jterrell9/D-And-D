package com.dd.network.protocol;

import com.dd.dd_util.BitPattern;
import com.dd.network.NetworkGameState;
import java.nio.ByteBuffer;
import java.util.Map;

public abstract class InstructionHandler {
    protected BitPattern bitPattern;
    public abstract void handleInstruction(ByteBuffer instruction, NetworkGameState gameState);
}
