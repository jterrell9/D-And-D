package com.dd.network.protocol;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitPattern;
import com.dd.dd_util.BitSequence;
import com.dd.network.NetworkGameState;

public abstract class InstructionHandler {
    protected BitPattern bitPattern;
    //Map<BitPattern, InstructionHandler> subInstructionHandlers

    public abstract void handleInstruction(ByteBuffer instruction, NetworkGameState gameState);
}
