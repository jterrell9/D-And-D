import java.awt.Point;
import java.io.PrintStream;

public class Main {
	public static void main(String[] args) {
		PrintStream console=new PrintStream(System.out);
		Map map=newMap();
		Player player=newPlayer("Jack");
		
		console.println(player);
		map.drawPlayer(player);
		
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
		
		/* So I got a OutOfMemoryError when I add this code.... it's late anyway, I'm out of memory myself
		console.println("\n>>user finds a Ring and types 'equip 'ring'");
		player.addtoInventory(ring);
		console.println(player);
		
		console.println("\n>>user finds a potion and types 'stores potion");
		player.addtoInventory(potion);
		console.println(player);
		*/
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
}
