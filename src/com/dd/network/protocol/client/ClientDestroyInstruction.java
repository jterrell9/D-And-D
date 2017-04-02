package com.dd.network.protocol.client;

<<<<<<< HEAD
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientDestroyInstruction extends InstructionHandler{
    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;
    }
=======
import com.dd.network.protocol.InstructionHandler;

public class ClientDestroyInstruction extends InstructionHandler{
>>>>>>> Add basic setup for GameServer managing multiple connections, a means of the blackbox communication class NetworkCommChannel, new GameState structures specifically designed for networked gameplay, and a layout for the instructions that will be used for pass messages between client and server.
}
