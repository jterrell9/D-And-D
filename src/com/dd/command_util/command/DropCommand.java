package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player;
import com.dd.entities.Equip;
import com.dd.entities.Player.EquipmentException;
import com.dd.levels.DungeonMap;
import com.dd.levels.Room;

public class DropCommand extends CommandHandler {
	private Player player;
	private DungeonMap map;

    public DropCommand(GameState gameState) {
    	player = gameState.getActivePlayer();
    	map = gameState.getMap();
	}

	@Override
	public void handleCommand(String commandName, String[] args, CommandOutputLog outputLog){
		Room room = map.getRoom(player.getPostion());
		if(args.length > 1) {
			outputLog.printToLog("Invalid arguments \""
									+ getArgsString(args)
									+ "\" passed to drop command.");
		}
		switch (args[0]) {
			case "lefthand":
				try {
					room.addItem(player.getLeftHand());
					player.discardEquipment(Equip.LEFTHAND);
					outputLog.printToLog(player.getName() + " has dropped their left hand\n");
				} catch (EquipmentException ee) {
					outputLog.printToLog(ee.toString() + "\n");
				}
				break;
			default:
				outputLog.printToLog("The body area \"" + args[0] + "\" is not a valid entry.\n"
						+ "Type \"help\" for help using the examine command.\n");
		}
		outputLog.printToLog(room.examineString());
	}
}