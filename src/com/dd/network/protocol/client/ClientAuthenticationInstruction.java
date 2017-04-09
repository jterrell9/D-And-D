package com.dd.network.protocol.client;

<<<<<<< HEAD
<<<<<<< master
<<<<<<< master
=======
>>>>>>> 24b74cad8d703ec7d7229ac01cea9dbf096cd485
import com.dd.network.NetworkGameState;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientAuthenticationInstruction extends InstructionHandler {
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;

    }
<<<<<<< HEAD
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
=======
>>>>>>> 24b74cad8d703ec7d7229ac01cea9dbf096cd485
}
