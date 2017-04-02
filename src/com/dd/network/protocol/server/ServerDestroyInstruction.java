package com.dd.network.protocol.server;

<<<<<<< master
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ServerDestroyInstruction extends InstructionHandler{
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ServerGameState gameState = (ServerGameState)netGameState;
    }
=======
import com.dd.network.protocol.InstructionHandler;

public class ServerDestroyInstruction extends InstructionHandler{
>>>>>>> HEAD~1
}
