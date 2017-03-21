package com.dd.tester;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dd.GameRunner;
import com.dd.GameState;
import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.items.Potion;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public class Command {
	
	Player player;
	DungeonMap map;
	
	String cmd=new String();
	String opt=new String();
	String[] opts=new String[10];
	int optNum;
	
	public Command(){
		this.player=GameRunner.getActiveGameState().getActivePlayer();
		this.map=GameRunner.getActiveGameState().getMap();
	}
	
	public void enterCommand() throws FileNotFoundException{
		promptParse();
		mapCmd();
	}
	
	public void promptParse(){
		Scanner user=new Scanner(System.in);
		System.out.print(player.getName()+">> ");
		String userInput=user.nextLine();
		String[] input=userInput.toLowerCase().split(" ");
		cmd=input[0];
		if(input.length>1 && input.length<11){
			for(int i=1;i<input.length;i++){
				opt+=input[i]+" ";
				opts[i-1]=input[i];
				if(isInteger(input[i])){
					optNum=Integer.parseInt(input[i]);
				}
				
			}
		}
	}
	
	public void mapCmd() throws FileNotFoundException {
		Item item;
		
		switch(cmd){
		
		case "quit":
			System.exit(0);
			return;
		
		case "menu":
			Tester.mainMenu();
			return;
		
		case "save":
			//GameState.save();
			Tester.mainMenu();
			return;
		
		case "attack":
			
			return;
			
		case "move":
			if(opts[0]!=null){
				switch(opts[0]){
				case "north": 
					player.mapPosition.moveNorth();
					return;
				case "south": 
					player.mapPosition.moveSouth();
					return;
				case "east": 
					player.mapPosition.moveNorth();
					return;
				case "west": 
					player.mapPosition.moveNorth();
					return;
				}
			}
			System.out.println("Type 'move' followed by north, south, east, or west");
			return;
				
		case "examine":
			switch(opts[0]){
			case "room":
				Tester.printLnTitle('~',"Examine Room",40);
				if(map.getRoom(player.getPostion()).isEmpty()){
					System.out.println("This room is empty");
					return;
				}
				/*if(currRoom.getMosterList()!=null){
					System.out.println("There is a "+currRoom.getMosterList().+" in this room!");
				}*/
				System.out.println("This room has the following items:");
				int i=1;
				for(String itemName:map.getRoom(player.getPostion()).getItemList()){
					System.out.println("Item "+i+": "+itemName);
					i++;
				}
				return;
			/*
			case "monster":
				Tester.printLnTitle('~',"Examine Monster",40);
				GameState.player.getRoom().getMonster().examine();
				return;
			*/
			default:
				break;
			}
			System.out.println("Type 'examine' followed by 'room' or 'monster'");
			return;
		/*		
		case "equip":
			
			if(opts[0].equals("item")){
				if(optNum<0){
					return;
				}
				item=GameState.player.getRoom().getItem(optNum);
				if(item instanceof Potion){
					System.out.println("You cannot equip a potion. You must pickup a potion");
					return;
				}
				GameState.player.equip(item);
				
				System.out.println();
				GameState.player.getRoom().examine();
			}
			else{
				System.out.println("Please type 'equip item' followed by the item number.");
			}
			return;	
				
		case "drop":
			if(opt!=null){
				if(opts[0].equals("inventory")){
					if(optNum<0){
						return;
					}
					GameState.player.drop(GameState.player.getInventoryItem(optNum));
					System.out.println();
					GameState.player.getRoom().examine();
					return;
				}
			}
				
			switch(opts[0]+" "+opts[1]){
			
			case "left hand":
				GameState.player.drop(Player.EQUIP.LEFTHAND);
				System.out.println();
				GameState.player.getRoom().examine();
				break;
			case "right hand":
				GameState.player.drop(Player.EQUIP.RIGHTHAND);
				System.out.println();
				GameState.player.getRoom().examine();
				break;
			}
			switch(opts[0]){
			case "hands":
				GameState.player.drop(Player.EQUIP.HANDS);
				System.out.println();
				GameState.player.getRoom().examine();
				return; 
			case "suit":
				GameState.player.drop(Player.EQUIP.SUIT);
				System.out.println();
				GameState.player.getRoom().examine();
				return;
			default:
				System.out.println("Type 'drop' followed by left hand, right hand, hands, suit, or inventory, followed by a number.");
				return;
			}
				
		case "pickup":	
			if(optNum<0){
				return;
			}
			item=GameState.player.getRoom().getItem(optNum);
			if(item==null){
				return;
			}
			if(!(item instanceof Potion)){
				System.out.println("You can only pickup potions");
				return;
			}
			GameState.player.pickup(item);
			System.out.println();
			GameState.player.getRoom().examine();
			return;
		
		case "use":
			switch(opts[0]){
			case "inventory":
				if(optNum<0){
					return;
				}
				item=GameState.player.getInventoryItem(optNum);
				GameState.player.usePotionFromInv(item);
				return;
			case "item":
				if(optNum<0){
					return;
				}
				item=GameState.player.getRoom().getItem(optNum);
				GameState.player.usePotion(item);
				return;
			}
			return;
		case "help":
			Tester.printLnTitle('*',"Commands",40);
			System.out.println("'quit' to quit\n"
					+"'menu'\n"
					+"'save'\n"
					+"'move' followed by a direction\n"
					+"'examine' followed by either 'room' or 'monster'\n"
					+"'equip' followed by 'item' or 'inventory' followed by a valid number\n"
					+"'drop' followed by player equip area, or 'inventory' followed by a valid number\n"
					+"'pickup' followed by 'item' followed by a valid number representing a potion\n"
					+"'use' followed by 'item' or 'inventory' followed by a valid number representing a potion\n"
					+"'attack'");
					
			return;
		*/
		default:
			System.out.println("This command not recognized\nType help for a list of commands");
			break;
		}
	}
	public boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch (NumberFormatException ex){
			return false;
		}
	}	
}