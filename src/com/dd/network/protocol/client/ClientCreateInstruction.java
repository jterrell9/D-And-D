package com.dd.network.protocol.client;

import com.dd.dd_util.BitPattern;
import com.dd.dd_util.ByteUtility;
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.protocol.InstructionHandler;
import java.nio.ByteBuffer;

public class ClientCreateInstruction extends InstructionHandler{
    private BitPattern gameStatePattern = new BitPattern(3, new byte[0x00]);
    private BitPattern dungeonMapPattern = new BitPattern(3, new byte[0x01]);
    private BitPattern roomPattern = new BitPattern(3, new byte[0x02]);
    private BitPattern playerPattern = new BitPattern(3, new byte[0x03]);
    private BitPattern monsterPattern = new BitPattern(3, new byte[0x04]);
    private BitPattern itemPattern = new BitPattern(3, new byte[0x05]);

    public ClientCreateInstruction(){
        bitPattern = new BitPattern(3, new byte[]{0x01});
    }

    @Override
    public void handleInstruction(ByteBuffer instruction, NetworkGameState netGameState){
        ClientGameState gameState = (ClientGameState)netGameState;
        int indexOffset = bitPattern.getBitLength() % 8;

        byte startByte = instruction.get(indexOffset++);
        byte[] objectIndex = new byte[]{ByteUtility.getBits(startByte, 3)};

        if(gameStatePattern.matchesBitPattern(objectIndex)){
            //ClientGameState newGameState = new ClientGameState();
        }
        else if(dungeonMapPattern.matchesBitPattern(objectIndex)){

        }
        else if(roomPattern.matchesBitPattern(objectIndex)){

        }
        else if(playerPattern.matchesBitPattern(objectIndex)){

        }
        else if(monsterPattern.matchesBitPattern(objectIndex)){

        }
        else if(itemPattern.matchesBitPattern(objectIndex)){

        }
    }
}
