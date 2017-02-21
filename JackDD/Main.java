import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		PrintStream console=new PrintStream(System.out);
		
		manualGame();
		
		Player player=loadGame();
		Map map=newMap();
		
		console.println(player);
		map.drawPlayer(player);
	}
	public static void manualGame() throws FileNotFoundException{
		//simulated new game procedure
		PrintStream console=new PrintStream(System.out);
		console.println("NEWGAME SELECTED");
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter name of player: ");
		Player player=newPlayer(scan.nextLine());
		Map map=newMap();
		
		//print out starting game stats.
		//this will go to the stats board and the map
		console.println(player);
		map.drawPlayer(player);
		
		//here the user enter's commands into the command console
		//notice that each command calls a single method, 
		//and then prints the stats to the statboard, and the map to map
		console.println("\n>>user types 'move north'");
		player.move(Map.DIR.NORTH,map);
		console.println(player);
		map.drawPlayer(player);
		
		console.println("\n>>user types 'move south'");
		player.move(Map.DIR.SOUTH,map);
		console.println(player);
		map.drawPlayer(player);
		
		Weapon sword=new Weapon("Sword",2,false);
		
		console.println("\n>>user finds sword and types 'equip sword'");
		player.equip(sword);
		console.println(player);
		
		Weapon twoHandedSword=new Weapon("2-Handed Sword",5,true);
		
		console.println("\n>>user finds a 2-handed sword and types 'equip '2-handed sword'");
		player.equip(twoHandedSword);
		console.println(player);
		
		console.println("\n>>user types 'drop left hand'");
		player.drop(Player.EQUIP.LEFTHAND);
		console.println(player);
		
		console.println("\n>>user types 'equip 2-handed sword'");
		player.equip(twoHandedSword);
		console.println(player);
		
		console.println("\n>>user types 'drop hands'");
		player.drop(Player.EQUIP.HANDS);
		console.println(player);
		
		Shield shield=new Shield("shield",4);
		Armour breastPlate=new Armour("Breast Plate",2);
		
		console.println("\n>>user finds a shield and types 'equip 'shield'");
		player.equip(shield);
		console.println(player);
		
		console.println("\n>>user finds a breast plate and types 'equip 'breast plate'");
		player.equip(breastPlate);
		console.println(player);
		
		console.println("\n>>user types 'equip sword'");
		player.equip(sword);
		console.println(player);
		
		console.println("\n>>user types 'move east'");
		player.move(Map.DIR.EAST,map);
		console.println(player);
		map.drawPlayer(player);
		
		SpecialItem ring=new SpecialItem("Ring",0,5,1,1);
		Potion potion=new Potion("Potion",4);
		
		console.println("\n>>user finds a Ring and types 'equip 'ring'");
		player.equip(ring);
		console.println(player);
		
		console.println("\n>>user finds a potion and types 'stores potion");
		player.addtoInventory(potion);
		console.println(player);

		console.println("\n>>user types 'use potion");
		player.usePotion(potion);
		console.println(player);
		
		console.println("\n>>user types 'save");
		save(player);
	}

	
	public static Player newPlayer(String name){
		Point startPosition=new Point();
		Stats startStats=new Stats(10,10,1,0);
		Player p=new Player(name,startPosition,startStats);
		return p;
	}
	public static Map newMap(){
		Map m=new Map(3,3);
		Point p=new Point();
		
		Room room00=new Room();
		m.addRoom(room00, p);
		
		Room room01=new Room();
		m.addRoomSouth(room01, p);
		
		Room room11=new Room();
		m.addRoomEast(room11, p);
		
		Room room21=new Room();
		m.addRoomEast(room21, p);
		
		Room room31=new Room();
		m.addRoomSouth(room31, p);
		
		return m;
	}
	public static void save(Player player) throws FileNotFoundException{
		File gsonFile=new File(player.getName()+".json");
		PrintStream toGsonFile=new PrintStream(gsonFile);
		toGsonFile.println(new Gson().toJson(player));
		toGsonFile.close();	
	}
	public static Player loadGame() throws FileNotFoundException{
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter name of player: ");
		File file=new File(scan.nextLine()+".json");
		scan.close();
		if(file.exists()){
			Scanner scanJsonFile=new Scanner(file);
			String avatarJson=scanJsonFile.nextLine();
			scanJsonFile.close();
			return new Gson().fromJson(avatarJson,Player.class);
		}
		System.out.println("ERROR file does not exit");
		return null;
	}
}
