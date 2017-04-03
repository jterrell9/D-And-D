package com.dd.network.protocol.client;

<<<<<<< master
<<<<<<< master
=======
>>>>>>> HEAD~0
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientDestroyInstruction extends InstructionHandler{
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;
    }
<<<<<<< master
=======
import com.dd.network.protocol.InstructionHandler;

public class ClientDestroyInstruction extends InstructionHandler{
>>>>>>> HEAD~1
=======
>>>>>>> HEAD~0
}
