package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;
import com.dd.levels.Room.UnknownItemException;

public class ExamineCommand extends CommandHandler {
	private Player player;
	private DungeonMap map;

    public ExamineCommand(GameState gameState) {
    	player = gameState.getActivePlayer();
    	map = gameState.getMap();
	}

    @Override
    public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException {
    	if(args[0] == null) {
    		throw new InvalidArgumentException("Choose something to examine. "
    				+ "Type \"help\" for help using the " + commandName +" command. ");
    	}
//WE CAN'T DO THIS EXCEPTION WITHOUT QUOTES
//    	if(args.length > 2) {
//    		throw new InvalidArgumentException("Type \"help\" for help using the " + commandName +" command. ");
//    	}
    	Room room = map.getRoom(player.getPostion());
    	switch(args[0].toLowerCase()) {
    	case "room":
			outputLog.printToLog(room.enterRoomText());
			break;
    	case "monsters":
		case "monster":
			if(room.hasMonster()) {
				room.getMonsterList().values().forEach((v) -> outputLog.printToLog(
						v.titleToString()
						+ "\nHealth: " + v.getStats().getHealth()
						+ "\nAttack/Defense: " + v.getStats().getAttack() + "/" + v.getStats().getDefense()
						+ "\n" + v.examineText()));
			}
			else {
				outputLog.printToLog("There are no monsters in this room. ");
			}
			break;
		case "item":
		case "items":
			room.examineItems();
			break;
		
		default:
			if(room.getMonster(args[0]) != null) {
				Monster monster = room.getMonster(args[0]);
				outputLog.printToLog(
						monster.titleToString() +". "
						+ "\nHealth: " + monster.getStats().getHealth()
						+ "\nAttack/Defense: " + monster.getStats().getAttack() + "/" + monster.getStats().getDefense()
						+ monster.examineText());
			}
			try{
				Item item = room.getItem(args[0]);
				outputLog.printToLog(item.titleToString() + " "
						+ item.examineToString() + "\n");
				break;
			}
			catch(UnknownItemException UIE) {
				outputLog.printToLog(UIE.toString());
    			return;
			}
    	}
	}
}