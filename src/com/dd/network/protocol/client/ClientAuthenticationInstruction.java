package com.dd.network.protocol.client;

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
import com.dd.network.protocol.InstructionHandler;

/**
 * Created by kyle on 4/2/17.
 */
public class ClientAuthenticationInstruction extends InstructionHandler {
>>>>>>> HEAD~1
}
