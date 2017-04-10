package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientPickupDestroyInstruction extends InstructionHandler{
    private ClientGameState gameState;

    public ClientPickupDestroyInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
        //To player index
        //Item type 3 bit
        //Item name index 6 bit
        //Item namemod 4 bit

        Player activePlayer = gameState.getActivePlayer();
        Player pickupPlayer = gameState.getPlayerAtIndex(instruction.getNextBits(4).getAsInt());
        Room room = gameState.getMap().getRoom(activePlayer.getPostion());
        Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
        int nameIndex = instruction.getNextBits(6).getAsInt();
        int nameMod = instruction.getNextBits(4).getAsInt();



        Item item = null;

        try {
            switch (itemType) {
                case ARTIFACT:
                    item = room.removeItem(DungeonMap.getBeholdNameAtIndex(nameIndex)
                                        + " Amulet"
                                        + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case MAGICAL:
                    item = room.removeItem(DungeonMap.getMagicNameAtIndex(nameIndex)
                                        + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case ONE_HANDED_WEAPON:
                    item = room.removeItem(DungeonMap.getOneSwordNameAtIndex(nameIndex)
                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case TWO_HANDED_WEAPON:
                    item = room.removeItem(DungeonMap.getTwoSwordNameAtIndex(nameIndex)
                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case POTION:
                    item = room.removeItem(DungeonMap.getPotionNameAtIndex(nameIndex)
                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case SHIELD:
                    item = room.removeItem(DungeonMap.getShieldNameAtIndex(nameIndex)
                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
                case SUIT:
                    item = room.removeItem(DungeonMap.getSuitNameAtIndex(nameIndex)
                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                    break;
            }
        }
        catch (Exception e){
            throw new InvalidInstructionException(e.getMessage());
        }

        outputLog.printToLog(pickupPlayer.getName() + " dropped " + item.getName());
    }
}
