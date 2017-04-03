package com.dd.network.protocol.server;

<<<<<<< master
<<<<<<< master
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ServerAuthenticationInstruction extends InstructionHandler {
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ServerGameState gameState = (ServerGameState)netGameState;
        //int regType = byte
    }
=======
=======
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
>>>>>>> HEAD~0
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ServerAuthenticationInstruction extends InstructionHandler {
<<<<<<< master
>>>>>>> HEAD~1
=======
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ServerGameState gameState = (ServerGameState)netGameState;
        //int regType = byte
    }
>>>>>>> HEAD~0
}
