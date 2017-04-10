package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitPattern;
import com.dd.dd_util.BitSequence;
import com.dd.entities.Equip;
import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.entities.monsters.*;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientDestroyInstruction extends InstructionHandler{
    private ClientGameState gameState;
    private BitPattern playerPattern = new BitPattern(3, new byte[]{0x00});
    private BitPattern itemPattern = new BitPattern(3, new byte[]{0x01});
    private BitPattern monsterPattern = new BitPattern(3, new byte[]{0x02});

    public ClientDestroyInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
        BitPattern argPattern = instruction.getNextBits(3);

        Player player = gameState.getActivePlayer();
        Room room = gameState.getMap().getRoom(player.getPostion());

        if(playerPattern.matchesBitPattern(argPattern)){
            //Player index 4 bit
            int index = instruction.getNextBits(4).getAsInt();
            gameState.removeActivePlayerAtIndex(index);
        }
        else if(itemPattern.matchesBitPattern(argPattern)){
            //Inventory/Room 1 bit
            //Item type index 3 bit
            //Item name 6 bit
            //Item namemod 4 bit
            int place = instruction.getNextBits(1).getAsInt();
            if(place == 0){
                //Inventory
                //Equip area 3 bit
                Equip equipArea = Equip.values()[instruction.getNextBits(3).getAsInt()];

                try {
                    if (equipArea == Equip.LEFTHAND
                            || equipArea == Equip.RIGHTHAND
                            || equipArea == Equip.HANDS
                            || equipArea == Equip.SUIT) {
                        player.removeEquipment(equipArea);
                    }
                    else if (equipArea == Equip.NONE) {
                        //Item type index 3 bit
                        //Item name 6 bit
                        //Item namemod 4 bit
                        Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
                        int nameIndex = instruction.getNextBits(6).getAsInt();
                        int nameMod = instruction.getNextBits(4).getAsInt();

                        switch(itemType){
                            case ARTIFACT:
                                player.removefromInventory(DungeonMap.getBeholdNameAtIndex(nameIndex)
                                                            + " Amulet"
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case MAGICAL:
                                player.removefromInventory(DungeonMap.getMagicNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case ONE_HANDED_WEAPON:
                                player.removefromInventory(DungeonMap.getOneSwordNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case TWO_HANDED_WEAPON:
                                player.removefromInventory(DungeonMap.getTwoSwordNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case POTION:
                                player.removefromInventory(DungeonMap.getPotionNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case SHIELD:
                                player.removefromInventory(DungeonMap.getShieldNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                            case SUIT:
                                player.removefromInventory(DungeonMap.getSuitNameAtIndex(nameIndex)
                                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                                break;
                        }
                    }
                    else {
                        throw new InvalidInstructionException("");
                    }
                }
                catch (Exception e){
                    outputLog.printToLog(e.getMessage());
                }
            }
            else if(place == 1) {
                //Room
                //Item type index 3 bit
                //Item name 6 bit
                //Item namemod 4 bit
                Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
                int nameIndex = instruction.getNextBits(6).getAsInt();
                int nameMod = instruction.getNextBits(4).getAsInt();

                try {
                    switch (itemType) {
                        case ARTIFACT:
                            room.removeItem(DungeonMap.getBeholdNameAtIndex(nameIndex)
                                                + " Amulet"
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case MAGICAL:
                            room.removeItem(DungeonMap.getMagicNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case ONE_HANDED_WEAPON:
                            room.removeItem(DungeonMap.getOneSwordNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case TWO_HANDED_WEAPON:
                            room.removeItem(DungeonMap.getTwoSwordNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case POTION:
                            room.removeItem(DungeonMap.getPotionNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case SHIELD:
                            room.removeItem(DungeonMap.getShieldNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                        case SUIT:
                            room.removeItem(DungeonMap.getSuitNameAtIndex(nameIndex)
                                                + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                            break;
                    }
                }
                catch (Exception e){
                    outputLog.printToLog(e.getMessage());
                }
            }
        }
        else if(monsterPattern.matchesBitPattern(argPattern)){
            //Monster type index 3 bit
            //Monster name 6 bit
            //Monster namemod 4 bit
            int monsterTypeIndex = instruction.getNextBits(3).getAsByte();
            int nameIndex = instruction.getNextBits(6).getAsByte();
            int nameMod = instruction.getNextBits(4).getAsInt();

            Monster.MonsterType monsterType = Monster.MonsterType.values()[monsterTypeIndex];

            try {
                switch (monsterType) {
                    case BEHOLDER:
                        room.removeMonster(DungeonMap.getBeholdNameAtIndex(nameIndex)
                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                        break;
                    case DRAGON:
                        room.removeMonster(DungeonMap.getDragNameAtIndex(nameIndex)
                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                        break;
                    case GOBLIN:
                        room.removeMonster(DungeonMap.getGobNameAtIndex(nameIndex)
                                            + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                        break;
                    case SKELETON:
                        room.removeMonster("skel" + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                        break;
                    case ZOMBIE:
                        room.removeMonster("zomb" + ((nameMod > 0) ? "_" + Integer.toString(nameMod) : ""));
                        break;
                    default:
                        throw new InvalidInstructionException("");
                }
            }
            catch (Exception e){
                outputLog.printToLog(e.getMessage());
            }
        }
        else{
            throw new InvalidInstructionException("");
        }
    }
}
