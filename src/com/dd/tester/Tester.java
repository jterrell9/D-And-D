package com.dd.tester;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dd.GameRunner;
import com.dd.GameState;
import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.items.Armour;
import com.dd.items.Artifact;
import com.dd.items.Item;
import com.dd.items.OneHandedWeapon;
import com.dd.items.Potion;
import com.dd.items.Shield;
import com.dd.items.TwoHandedWeapon;
import com.dd.items.Weapon;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public class Tester {
	
	private static String cmd=new String();
	private static String opt=new String();
	private static String[] opts=new String[10];
	private static int optNum;
	
	public static void go() throws FileNotFoundException{
		
		mainMenu();
		printStats();
		printMap();
		System.out.println("*Type help for a list of commands*");
		cmdLoop();
	
	}
	public static void cmdLoop() throws FileNotFoundException {
		while(true){
			enterCommand();
			System.out.println();
			printStats();
			printMap();
		}
	}
	public static void mainMenu() throws FileNotFoundException{
		printLnTitle('*',"Main Menu",40);
		System.out.println("Please select from the following:"
				+"\n1: New Game"
				+"\n2: Resume Game"
				+"\n3: Quit");
		System.out.print("Enter Selection >>");
		try{
			Scanner scanInt=new Scanner(System.in);
			int selection=scanInt.nextInt();
			String name;
			if(selection==1){		//new game
				Scanner scanName=new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.next();
				GameState game=new GameState(name,new Player(name),new DungeonMap(5,5));
				GameRunner.registerGameState(game);
				GameRunner.setActiveGameState(game);
				populate5x5(); //adds rooms to map;
			}else if(selection==2){		//load game
				Scanner scanName=new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.nextLine();
				//game.setMap(loadMap(name));
				//game.addActivePlayer(loadPlayer(name));
			}else if(selection==3){		//quit
				System.out.println("Thank you for playing! GoodBye!");
				System.exit(0);
			}else{
				System.out.println("\n!e:Invalid entry, please try again.\n");
				mainMenu();
			}
		}catch(InputMismatchException ime){
			System.out.println("\n!e:Invalid entry, please try again.\n");
			mainMenu();
		}
	}
	
	public static void enterCommand() throws FileNotFoundException{
		promptParse();
		mapCmd();
	}
	
	public static void mapCmd() throws FileNotFoundException {
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
					if(getRunnerMap().isRoomInDir(getRunnerPosition(),DIR.NORTH)){
						getRunnerPosition().moveNorth();
					}else{
						System.out.println("No Door in that Direction!");
					}
					return;
				case "south": 
					if(getRunnerMap().isRoomInDir(getRunnerPosition(),DIR.SOUTH)){
						getRunnerPosition().moveSouth();
					}else{
						System.out.println("No Door in that Direction!");
					}
					return;
				case "east": 
					if(getRunnerMap().isRoomInDir(getRunnerPosition(),DIR.EAST)){
						getRunnerPosition().moveEast();
					}else{
						System.out.println("No Door in that Direction!");
					}
					return;
				case "west": 
					if(getRunnerMap().isRoomInDir(getRunnerPosition(),DIR.WEST)){
						getRunnerPosition().moveWest();
					}else{
						System.out.println("No Door in that Direction!");
					}
					return;
				}
			}
			System.out.println("Type 'move' followed by north, south, east, or west");
			return;
				
		case "examine":
			switch(opts[0]){
			case "room":
				Tester.printLnTitle('~',"Examine Room",40);
				if(getRunnerMap().isRoom(getRunnerPosition())){
					if(!getRunnerRoom().getMosterList().isEmpty()){
						System.out.println("This Room Has A Monster:");
						for(String monsterName:getRunnerRoom().getMosterList()){
							System.out.println(monsterName);
						}
					}
					if(!getRunnerRoom().getItemList().isEmpty()){
						System.out.println("This room has the following items:");
						int i=1;
						for(String itemName:getRunnerRoom().getItemList()){
							System.out.println("Item "+i+": "+itemName);
							i++;
						}
						return;
					}else{
						System.out.println("This room is empty.");
						return;
					}
				}else{
					System.out.println("ERROR no room here!");
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
	
	public static void promptParse(){
		Scanner user=new Scanner(System.in);
		System.out.print(getRunnerPlayer().getName()+">> ");
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
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		}catch (NumberFormatException ex){
			return false;
		}
	}
	public static void printStats(){
		printLnTitle('~',getRunnerPlayer().getName()+"'s Stats Board",40);
		System.out.println(getRunnerPlayer().statboardToString());
	}
	public static void printMap(){
		printLnTitle('-',"Map",40);
		MapPosition playerPos=getRunnerPlayer().getPostion();
		for(int y=0;y<getRunnerMap().getMaxRow();y++){
			for(int x=0;x<getRunnerMap().getMaxCol();x++){
				if(x==0)
					System.out.print("\t|");
				if(playerPos.getX()==x && playerPos.getY()==y)
					System.out.print("#");
				else if(getRunnerMap().isRoom(new MapPosition(x,y)))
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.print("|\n");
		}
		printLnTitle('-',"",40);
	}
	public static void printLnTitle(char c,String str,int width){
		int strLength=str.length();
		int startIndex=(width/2)-(strLength/2);
		for(int i=0;i<=width;i++){
			if(i==startIndex){
				System.out.print(str);
				i+=strLength;
			}else{
				System.out.print(c);
			}
		}
		System.out.println();
	}
	
	public static void populate5x5(){
		MapPosition buildPos=new MapPosition();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveEast();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveEast();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveSouth();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveSouth();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveEast();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveSouth();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveSouth();
		getRunnerMap().addRoom(new Room(),buildPos);
		buildPos.moveEast();
		getRunnerMap().addRoom(new Room(),buildPos);
		
		
	}
	
	public static DungeonMap old5x5(){
		DungeonMap maze=new DungeonMap(5,5);
		MapPosition buildPosition=new MapPosition();
		
		Room room00=new Room();
		OneHandedWeapon sword=new OneHandedWeapon("Sword",2);
		room00.addItem(sword);
		Shield shield=new Shield("shield",4);
		room00.addItem(shield);
		Artifact ring=new Artifact("Ring",0,5,1,1);
		room00.addItem(ring);
		Potion potion=new Potion("Health Elixer",10);
		room00.addItem(potion);
		maze.addRoom(room00, buildPosition);
		
		Room room01=new Room();
		Armour breastPlate=new Armour("Breast Plate",2);
		room01.addItem(breastPlate);
		buildPosition.moveSouth();
		maze.addRoom(room01, buildPosition);
		
		Room room11=new Room();
		Artifact ring2=new Artifact("Ring",0,5,1,1);
		room11.addItem(ring2);
		room11.addItem(potion);
		buildPosition.moveEast();
		maze.addRoom(room11, buildPosition);
		addRoom(buildPosition,DIR.EAST);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.EAST);
		addRoom(buildPosition,DIR.EAST);
		
		return maze;
	}
	public static void addRoom(MapPosition startPos,DIR dir){
		Room room=new Room();
		switch(dir){
		case NORTH:
			startPos.moveNorth();
			break;
		case SOUTH:
			startPos.moveSouth();
			break;
		case EAST:
			startPos.moveEast();
			break;
		case WEST:
			startPos.moveWest();
			break;
		}
		getRunnerMap().addRoom(room, startPos);
	}

	public static Player getRunnerPlayer(){
		return GameRunner.getActiveGameState().getActivePlayer();
	}
	public static DungeonMap getRunnerMap(){
		return GameRunner.getActiveGameState().getMap();
	}
	public static MapPosition getRunnerPosition(){
		return getRunnerPlayer().getPostion();
	}
	public static Room getRunnerRoom(){
		return getRunnerMap().getRoom(getRunnerPosition());
	}
}
