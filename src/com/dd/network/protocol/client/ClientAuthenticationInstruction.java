package com.dd.network.protocol.client;

<<<<<<< master
<<<<<<< master
import com.dd.network.NetworkGameState;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientAuthenticationInstruction extends InstructionHandler {
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;

    }
=======
=======
import com.dd.network.NetworkGameState;
import com.dd.network.ClientGameState;
>>>>>>> HEAD~0
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientAuthenticationInstruction extends InstructionHandler {
<<<<<<< master
>>>>>>> HEAD~1
=======
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;

    }
>>>>>>> HEAD~0
}
