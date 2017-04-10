package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientUseInstruction extends InstructionHandler{
    private ClientGameState gameState;

    public ClientUseInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog) {
        //Item name index 6 bit
        //Item name mod 4 bit
        //From player/room 1 bit

        int nameIndex = instruction.getNextBits(6).getAsInt();
        int nameMod = instruction.getNextBits(4).getAsInt();
        int useFrom = instruction.getNextBits(1).getAsInt();

        Player player = gameState.getActivePlayer();

        if(useFrom == 0){
            //Room
            Room room = gameState.getMap().getRoom(player.getPostion());
            player.usePotion(room.getItem(DungeonMap.getPotionNameAtIndex(nameIndex)
                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : "")));
        }
        else if(useFrom == 1){
            //Player
            try {
                player.usePotionFromInventory(DungeonMap.getPotionNameAtIndex(nameIndex)
                        + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
            }
            catch (Player.InventoryException e){
                outputLog.printToLog(e.getMessage());
            }
        }
    }
}
