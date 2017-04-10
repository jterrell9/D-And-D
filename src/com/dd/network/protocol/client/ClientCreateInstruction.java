package com.dd.network.protocol.client;

import com.dd.Stats;
import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitPattern;
import com.dd.dd_util.BitSequence;
import com.dd.dd_util.ByteUtility;
import com.dd.entities.Equip;
import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.entities.monsters.*;
import com.dd.items.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.ServerGameState;
import com.dd.network.ClientGameState;
import com.dd.network.NetworkGameState;
import com.dd.network.protocol.InstructionHandler;

import java.util.function.DoubleUnaryOperator;

public class ClientCreateInstruction extends InstructionHandler{
    private BitPattern gameStatePattern = new BitPattern(3, new byte[0x00]);
    private BitPattern dungeonMapPattern = new BitPattern(3, new byte[0x01]);
    private BitPattern roomPattern = new BitPattern(3, new byte[0x02]);
    private BitPattern playerPattern = new BitPattern(3, new byte[0x03]);
    private BitPattern monsterPattern = new BitPattern(3, new byte[0x04]);
    private BitPattern itemPattern = new BitPattern(3, new byte[0x05]);

    private ClientGameState gameState;

    public ClientCreateInstruction(ClientGameState gameState){
        this.gameState = gameState;
        bitPattern = new BitPattern(3, new byte[]{0x01});
    }

    @Override
    public void handleInstruction(BitSequence instruction, CommandOutputLog outputLog){
        BitPattern argPattern = instruction.getNextBits(3);

        if (gameStatePattern.matchesBitPattern(argPattern)) {
            //NOT NEEDED BECAUSE GAMESTATE IS CREATED BY DEFAULT
            /*
            //Name length
            BitPattern nameLenPattern = instruction.getNextBits(6);
            byte nameLen = nameLenPattern.getAsByte();
            if(nameLen > 63){
                throw new InvalidInstructionException("");
            }
            BitPattern namePattern = instruction.getNextBits(nameLen * 8);
            String name = namePattern.getAsString();
            gameState.setName(name);
            */
        }
        else if (dungeonMapPattern.matchesBitPattern(argPattern)) {
            //ALSO NOT NEEDED, CAN BE DONE BY DEFAULT
        }
        else if (roomPattern.matchesBitPattern(argPattern)) {
            //need 5 bits for each position
            int x = instruction.getNextBits(4).getAsByte();
            int y = instruction.getNextBits(4).getAsByte();
            gameState.getMap().addRoom(new Room(), new MapPosition(x, y));
        }
        else if (playerPattern.matchesBitPattern(argPattern)) {
            //NEEDS AN INDEX ATTRIBUTE 4 bit
            //ADD SOMETHING TO HANDLE ADDING PLAYERS THAT ARENT THE MAIN PLAYER
            //namelen 5 bits
            //name
            //x 4 bits
            //y 4 bits
            BitPattern nameLenPattern = instruction.getNextBits(5);
            int nameLen = nameLenPattern.getAsByte();
            //Verify max name length of 30 characters is being met
            if(nameLen > 0x1E || nameLen == 0){
                throw new InvalidInstructionException("");
            }
            BitPattern namePattern = instruction.getNextBits(nameLen * 8);

            String name = namePattern.getAsString();
            int x = instruction.getNextBits(4).getAsByte();
            int y = instruction.getNextBits(4).getAsByte();

            Player player = gameState.getActivePlayer();
            player.setName(name);
            player.setMapPosition(new MapPosition(x, y));
        }
        else if (monsterPattern.matchesBitPattern(argPattern)) {
            //NEEDS AN INDEX ATTRIBUTE 10 bit
            //Name index 6bit
            //X 4bits
            //y 4bit
            //health 8bits
            //maxHealth 8 bits
            //attack 8 bits
            //defense f8 bits
            //Can use name index to determine name of monster
            int monsterTypeIndex = instruction.getNextBits(3).getAsByte();
            int nameIndex = instruction.getNextBits(6).getAsByte();
            int x = instruction.getNextBits(4).getAsByte();
            int y = instruction.getNextBits(4).getAsByte();
            int health = instruction.getNextBits(8).getAsByte();
            int maxHealth = instruction.getNextBits(8).getAsByte();
            int attack = instruction.getNextBits(8).getAsByte();
            int defense = instruction.getNextBits(8).getAsByte();

            Monster.MonsterType monsterType = Monster.MonsterType.values()[monsterTypeIndex];

            Monster monster = null;

            switch(monsterType){
                case BEHOLDER:
                    monster = new Beholder(DungeonMap.getBeholdNameAtIndex(nameIndex),
                                            maxHealth,
                                            attack,
                                            defense);
                    break;
                case DRAGON:
                    monster = new Dragon(DungeonMap.getDragNameAtIndex(nameIndex),
                                            maxHealth,
                                            attack,
                                            defense);
                    break;
                case GOBLIN:
                    monster = new Goblin(DungeonMap.getGobNameAtIndex(nameIndex) + " the goblin",
                                            maxHealth,
                                            attack,
                                            defense);
                    break;
                case SKELETON:
                    monster = new Skeleton("skel", maxHealth, attack, defense);
                    break;
                case ZOMBIE:
                    monster = new Zombie("zomb", maxHealth, attack, defense);
                    break;
                default:
                    throw new InvalidInstructionException("");
            }

            if(maxHealth != health){
                Stats currentStats = new Stats(health, maxHealth, attack, defense);
                monster.setStats(currentStats);
            }

            Room room = gameState.getMap().getRoom(new MapPosition(x, y));
            room.addMonster(monster);
        }
        else if (itemPattern.matchesBitPattern(argPattern)) {
            //Item type 3 bit
            //Item name index 6 bit
            //Health 8 bit
            //MaxHealth 8 bit
            //Attack 8 bit
            //Defense 8 bit
            //Player/Room 1 bit

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

            int placement = instruction.getNextBits(1).getAsInt();

            if(placement == 0){
                //Room
                //x 4 bit
                //y 4 bit
                int x = instruction.getNextBits(4).getAsInt();
                int y = instruction.getNextBits(4).getAsInt();

                gameState.getMap().getRoom(new MapPosition(x, y)).addItem(item);
            }
            else if(placement == 1){
                //Player
                //Equip area 3 bit
                Equip equipArea = Equip.values()[instruction.getNextBits(3).getAsInt()];

                Player player = gameState.getActivePlayer();

                try {
                    if (equipArea == Equip.LEFTHAND
                            || equipArea == Equip.RIGHTHAND
                            || equipArea == Equip.HANDS
                            || equipArea == Equip.SUIT) {
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