package com.dd.network.protocol.server;

<<<<<<< HEAD
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
import com.dd.network.protocol.InstructionHandler;

/**
 * Created by kyle on 4/2/17.
 */
public class ServerAuthenticationInstruction extends InstructionHandler {
>>>>>>> Add basic setup for GameServer managing multiple connections, a means of the blackbox communication class NetworkCommChannel, new GameState structures specifically designed for networked gameplay, and a layout for the instructions that will be used for pass messages between client and server.
}
