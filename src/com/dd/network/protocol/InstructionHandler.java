package com.dd.network.protocol;

<<<<<<< HEAD
import com.dd.network.NetworkGameState;

import java.nio.ByteBuffer;

public abstract class InstructionHandler {
    public abstract void handleInstruction(ByteBuffer instruction, NetworkGameState gameState);
=======
/**
 * Created by kyle on 4/2/17.
 */
public class InstructionHandler {
>>>>>>> Add basic setup for GameServer managing multiple connections, a means of the blackbox communication class NetworkCommChannel, new GameState structures specifically designed for networked gameplay, and a layout for the instructions that will be used for pass messages between client and server.
}
