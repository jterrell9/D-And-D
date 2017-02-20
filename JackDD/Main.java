import java.awt.Point;

public class Main {
	public enum Direction{
		NORTH,SOUTH,EAST,WEST
	}

	public static void main(String[] args) {
		Map map=newMap();
		Player player=newPlayer("Jack");
		
		System.out.println(player);
		map.drawPlayer(player);
		
		player.move("s",map);
		System.out.println(player);
		map.drawPlayer(player);
		
		player.move("s",map);
		System.out.println(player);
		map.drawPlayer(player);
		
		Weapon sword=new Weapon("Sword",2,false);
		player.equip(sword);
		System.out.println(player);
		
		Weapon twoHandedSword=new Weapon("2-Handed Sword",5,true);
		
		player.equip(twoHandedSword);
		System.out.println(player);
		
		player.drop("hand1");
		player.equip(twoHandedSword);
		System.out.println(player);
		
		player.drop("hands");
		
		Armour breastPlate=new Armour("Breast Plate",2);
		
		player.equip(breastPlate);
		player.equip(sword);
		System.out.println(player);
		
		player.move("east",map);
		map.drawPlayer(player);
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
