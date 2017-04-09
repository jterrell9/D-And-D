package com.dd.network.protocol.server;

import com.dd.dd_util.BitPattern;
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ServerAuthenticationInstruction extends InstructionHandler {
    public ServerAuthenticationInstruction(){
        bitPattern = new BitPattern(3, new byte[]{0x00});
    }

    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        int indexOffset = bitPattern.getBitLength();
        ServerGameState gameState = (ServerGameState)netGameState;
        ByteOrder byteOrder = instruction.order();
        if(byteOrder == ByteOrder.LITTLE_ENDIAN){
            instruction = instruction.order(ByteOrder.BIG_ENDIAN);
        }

        byte regBit = instruction.get(indexOffset++);
        //Check for activation
        if((regBit & (byte)0x80) == (byte)0x80){

        }
        //Handle registration
        else{

        }

    }
}
