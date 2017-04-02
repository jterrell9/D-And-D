package com.dd.network.protocol.client;

<<<<<<< master
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientMoveInstruction extends InstructionHandler{
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;
    }
=======
import com.dd.network.protocol.InstructionHandler;

public class ClientMoveInstruction extends InstructionHandler{
>>>>>>> HEAD~1
}
