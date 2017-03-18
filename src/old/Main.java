package old;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {
	
	static PrintStream output=new PrintStream(System.out);
	static Scanner input=new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException {
		output.println("Welcome To Dungeons and D&D\n");
		go();
	}
	public static void go() throws FileNotFoundException{
		Player player=mainMenu();
			
		output.println("Here are "+player.getName()+"'s initial stats:\n");
		output.println(player);
		output.println("\nMap:");
		player.getMap().drawPlayer(player);
		
		command(player);
	}
	
	public static void command(Player currPlayer) throws FileNotFoundException
	{
		Command command=new Command(currPlayer);
		while(true){
			output.println("\n<--------enter a command-------->");
			command.enterCommand();
			
			output.println();
			output.println(currPlayer);
			
			output.println("\nMap:");
			currPlayer.getMap().drawPlayer(currPlayer);
		}
	}
	
	public static Player mainMenu() throws FileNotFoundException{
		String name;
		Player playerSelect=new Player();
		Map map=new Map();
		output.println("\nPlease select from the following:"
				+"\n1: New Game"
				+"\n2: Resume Game"
				+"\n3: Quit");
		output.print("Enter Selection >>");
		Scanner scanInt=new Scanner(System.in);
		int selection=scanInt.nextInt();
		Scanner scanName=new Scanner(System.in);
		if(selection==1){		//new game
			output.print("Enter Player's Name: ");
			name=scanName.next();
			playerSelect=newGame(name,newMap());
		}else if(selection==2){		//load game
			output.print("Enter Player's Name: ");
			name=scanName.nextLine();
			playerSelect=loadPlayer(name);
			///map=loadMap(name);
			//map=playerSelect.getMap();
			map=newMap();
			playerSelect.setMap(map);
		}else if(selection==3){		//quit
			output.println("Thank you for playing! GoodBye!");
			System.exit(0);
		}else{
			output.println("Invalid entry, please try again.");
			mainMenu();
		}
		return playerSelect;
	}
	
	public static Player newGame(String name,Map map){
		Point startPosition=new Point();
		Stats startStats=new Stats(10,10,1,0);
		Player p=new Player(name,startPosition,startStats,map);
		return p;
	}
	public static Map newMap(){
		Map map3x3=new Map(3,3);
		Point p=new Point();
		
		Room room00=new Room();
		Weapon sword=new Weapon("Sword",2,false);
		room00.addItem(sword);
		Shield shield=new Shield("shield",4);
		room00.addItem(shield);
		Artifact ring=new Artifact("Ring",0,5,1,1);
		room00.addItem(ring);
		Potion potion=new Potion("Health Elixer",10);
		room00.addItem(potion);
		map3x3.addRoom(room00, p);
		
		Room room01=new Room();
		Weapon twoHandedSword=new Weapon("2-Handed Sword",5,true);
		room01.addItem(twoHandedSword);
		Armour breastPlate=new Armour("Breast Plate",2);
		room01.addItem(breastPlate);
		Monster dragon=new Monster("Dragon",8,3,2);
		room01.addMonster(dragon);
		map3x3.addRoomDir(room01, p,Map.DIR.SOUTH);
		
		Room room11=new Room();
		Artifact ring2=new Artifact("Ring",0,5,1,1);
		room11.addItem(ring2);
		room11.addItem(potion);
		map3x3.addRoomDir(room11, p,Map.DIR.EAST);
		
		Room room21=new Room();
		map3x3.addRoomDir(room21, p,Map.DIR.EAST);
		
		Room room31=new Room();
		map3x3.addRoomDir(room31, p,Map.DIR.SOUTH);
		
		return map3x3;
	}
	public static Player loadPlayer(String name) throws FileNotFoundException{
		File playerFile=new File(name+".json");
		if(playerFile.exists()){
			Scanner scanJsonFile=new Scanner(playerFile);
			if(scanJsonFile.hasNextLine()){
				String playerJson=scanJsonFile.nextLine();
				return new Gson().fromJson(playerJson,Player.class);
			}else{
				output.println("ERROR Json file is empty!");
				return null;
			}
		}
		System.out.println("ERROR file does not exit");
		return null;
	}
	public static Map loadMap(String name) throws FileNotFoundException{
		File mapFile=new File(name+".map.json");
		if(mapFile.exists()){
			Scanner scanJsonFile=new Scanner(mapFile);
			String mapJson=scanJsonFile.nextLine();
			return new Gson().fromJson(mapJson,Map.class);
		}
		System.out.println("ERROR file does not exit");
		return null;
	}
}
