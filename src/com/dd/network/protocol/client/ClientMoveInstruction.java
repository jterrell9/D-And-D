package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitPattern;
import com.dd.dd_util.BitSequence;
import com.dd.entities.Equip;
import com.dd.entities.Player;
import com.dd.entities.monsters.Beholder;
import com.dd.items.Artifact;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientMoveInstruction extends InstructionHandler{
    private ClientGameState gameState;
    private BitPattern playerPattern = new BitPattern(1, new byte[0x00]);
    private BitPattern itemPattern = new BitPattern(1, new byte[0x01]);

    public ClientMoveInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
        BitPattern argPattern = instruction.getNextBits(1);

        Player player = gameState.getActivePlayer();
        if (playerPattern.matchesBitPattern(argPattern)){
            //x 4 bit
            //y 4 bit
            int x = instruction.getNextBits(4).getAsInt();
            int y = instruction.getNextBits(4).getAsInt();
            player.setMapPosition(new MapPosition(x, y));
            //MAYBE ADD EXAMINE OUTPUT?
        }
        else if(itemPattern.matchesBitPattern(argPattern)){
            //From/To player 1 bit
            //left, right, hands, suit , or inventory 3 bit
            //THE FOLLOWING ARE ONLY NEEDED IF THE ITEM IS IN THE INVENTORY
            Room room = gameState.getMap().getRoom(player.getPostion());

            int dir = instruction.getNextBits(1).getAsByte();
            Equip equipArea = Equip.values()[instruction.getNextBits(3).getAsInt()];


            if(dir == 0){
                //From player
                try {
                    if (equipArea == Equip.LEFTHAND
                            || equipArea == Equip.RIGHTHAND
                            || equipArea == Equip.HANDS
                            || equipArea == Equip.SUIT) {
                        room.addItem(player.removeEquipment(equipArea));
                    }
                    else if (equipArea == Equip.NONE) {
                        //item type 3 bit
                        //item name index 6 bit
                        //item namemod 4 bit
                        Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
                        int nameIndex = instruction.getNextBits(6).getAsInt();
                        int nameMod = instruction.getNextBits(4).getAsInt();

                        Item item = null;
                        switch(itemType){
                            case ARTIFACT:
                                item = player.removefromInventory(DungeonMap.getBeholdNameAtIndex(nameIndex)
                                                                    + " Amulet"
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case MAGICAL:
                                item = player.removefromInventory(DungeonMap.getMagicNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case ONE_HANDED_WEAPON:
                                item = player.removefromInventory(DungeonMap.getOneSwordNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case TWO_HANDED_WEAPON:
                                item = player.removefromInventory(DungeonMap.getTwoSwordNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case POTION:
                                item = player.removefromInventory(DungeonMap.getPotionNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case SHIELD:
                                item = player.removefromInventory(DungeonMap.getShieldNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case SUIT:
                                item = player.removefromInventory(DungeonMap.getSuitNameAtIndex(nameIndex)
                                                                    + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                        }
                        room.addItem(item);
                    }
                    else {
                        throw new InvalidInstructionException("");
                    }
                }
                catch (Exception e){
                    outputLog.printToLog(e.getMessage());
                }
            }
            else if(dir == 1){
                //To player
                //item type 3 bit
                //item name index 6 bit
                //item namemod 4 bit
                Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
                int nameIndex = instruction.getNextBits(6).getAsInt();
                int nameMod = instruction.getNextBits(4).getAsInt();

                try {
                    Item item = null;
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
                    if(equipArea == Equip.LEFTHAND
                            || equipArea == Equip.RIGHTHAND
                            || equipArea == Equip.HANDS
                            || equipArea == Equip.SUIT){
                        player.equip(item);
                    }
                    else if(equipArea == Equip.NONE){
                        player.addtoInventory(item);
                    }
                    else{
                        throw new InvalidInstructionException("");
                    }
                }
                catch (Exception e){
                    outputLog.printToLog(e.getMessage());
                }
            }
        }
    }
}
