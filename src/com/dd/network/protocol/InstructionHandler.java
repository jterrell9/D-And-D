package com.dd.network.protocol;

<<<<<<< HEAD
import com.dd.dd_util.BitPattern;
import com.dd.network.NetworkGameState;

import java.nio.ByteBuffer;
import java.util.Map;

public abstract class InstructionHandler {
    protected BitPattern bitPattern;
    //Map<BitPattern, InstructionHandler> subInstructionHandlers

=======
import com.dd.network.NetworkGameState;

import java.nio.ByteBuffer;

public abstract class InstructionHandler {
>>>>>>> 24b74cad8d703ec7d7229ac01cea9dbf096cd485
    public abstract void handleInstruction(ByteBuffer instruction, NetworkGameState gameState);
}
