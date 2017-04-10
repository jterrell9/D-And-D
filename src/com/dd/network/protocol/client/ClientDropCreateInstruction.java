package com.dd.network.protocol.client;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitSequence;
import com.dd.entities.Equip;
import com.dd.entities.Player;
import com.dd.items.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.protocol.InstructionHandler;

public class ClientDropCreateInstruction extends InstructionHandler{
    private ClientGameState gameState;

    public ClientDropCreateInstruction(ClientGameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
        //From player index 4 bit
        //Item type 3 bit
        //Item name index 6 bit
        //Health 8 bit
        //MaxHealth 8 bit
        //Attack 8 bit
        //Defense 8 bit

        Player activePlayer = gameState.getActivePlayer();
        Player dropPlayer = gameState.getPlayerAtIndex(instruction.getNextBits(4).getAsInt());
        Room room = gameState.getMap().getRoom(activePlayer.getPostion());
        Item.ItemType itemType = Item.ItemType.values()[instruction.getNextBits(3).getAsInt()];
        int itemNameIndex = instruction.getNextBits(6).getAsInt();

        Item item = null;

        int healthGain = instruction.getNextBits(8).getAsInt();
        int maxHealthGain = instruction.getNextBits(8).getAsInt();
        int attackGain = instruction.getNextBits(8).getAsInt();
        int defenseGain = instruction.getNextBits(8).getAsInt();

        switch(itemType){
            case ARTIFACT:
                item = new Artifact(DungeonMap.getBeholdNameAtIndex(itemNameIndex) + " Amulet",
                        healthGain,
                        maxHealthGain,
                        attackGain,
                        defenseGain);
                break;
            case MAGICAL:
                //Body area 2 bit (Should probably remove
                Equip bodyArea = Equip.values()[instruction.getNextBits(2).getAsInt()];
                item = new Magical(DungeonMap.getMagicNameAtIndex(itemNameIndex),
                        bodyArea,
                        healthGain,
                        maxHealthGain,
                        attackGain,
                        defenseGain);
                break;
            case ONE_HANDED_WEAPON:
                item = new OneHandedWeapon(DungeonMap.getOneSwordNameAtIndex(itemNameIndex), attackGain);
                break;
            case TWO_HANDED_WEAPON:
                item = new TwoHandedWeapon(DungeonMap.getTwoSwordNameAtIndex(itemNameIndex), attackGain);
                break;
            case POTION:
                item = new Potion(DungeonMap.getPotionNameAtIndex(itemNameIndex), healthGain);
                break;
            case SHIELD:
                item = new Shield(DungeonMap.getShieldNameAtIndex(itemNameIndex), defenseGain);
                break;
            case SUIT:
                item = new Suit(DungeonMap.getSuitNameAtIndex(itemNameIndex), defenseGain);
                break;
        }

        room.addItem(item);
    }
}
