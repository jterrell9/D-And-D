package com.dd.network.protocol.client;

<<<<<<< HEAD
<<<<<<< master
<<<<<<< master
=======
>>>>>>> HEAD~0
=======
>>>>>>> 24b74cad8d703ec7d7229ac01cea9dbf096cd485
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientDestroyInstruction extends InstructionHandler{
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;
    }
<<<<<<< HEAD
<<<<<<< master
=======
import com.dd.network.protocol.InstructionHandler;

public class ClientDestroyInstruction extends InstructionHandler{
>>>>>>> HEAD~1
=======
>>>>>>> HEAD~0
=======
>>>>>>> 24b74cad8d703ec7d7229ac01cea9dbf096cd485
}
