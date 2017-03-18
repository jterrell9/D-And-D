
public class DungeonMap {
	
	
	public Room[][] rooms;
	public int maxRow;
	public int maxCol;
	public DungeonMap(int maxCol,int maxRow){
		this.maxRow=maxRow;
		this.maxCol=maxCol;
		rooms=new Room[maxRow][maxCol];
	}
	
	public boolean isRoom(MapPosition p){
		if(isOutOfBounds(p)){
			System.out.println("!e.DungeounMap.isRoom():out of bounds");
			return false;
		}
		if(rooms[p.y][p.x]==null){
			return false;
		}else{
			return true;
		}
	}
	
	public void drawDungeon(Player player){
		MapPosition pos=player.getPostion();
		for(int y=0;y<maxRow;y++){
			for(int x=0;x<maxCol;x++){
				if(x==0)
					System.out.print("\t	|");
				if(pos.x==x && pos.y==y)
					System.out.print("#");
				else if(rooms[y][x]!=null)
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.print("|\n");
		}
	}
	
	public void addRoom(Room room,MapPosition position){
		if(isOutOfBounds(position)){
			System.out.println("!e:DungeonMap.addRoom():out of bounds of map");
			return;
		}
		if(rooms[position.y][position.x]!=null){
			System.out.println("!e:DungeonMap.addRoom():room already exitsts at MapPosition "+position.x+","+position.y);
			return;
		}
		rooms[position.y][position.x]=room;
	}
	public MapPosition addRoomDir(Room room,MapPosition p,DIR d){
		switch(d){
		case NORTH:
			p.translate(DIR.NORTH);
			break;
		case SOUTH:
			p.translate(DIR.SOUTH);
			break;
		case EAST:
			p.translate(DIR.EAST);
			break;
		case WEST:
			p.translate(DIR.WEST);
			break;
		default:
			break;
		}
		if(isOutOfBounds(p)){
			System.out.println("!e:DungeonMap.addRoomDir():out of bounds of map");
			return p;
		}
		if(rooms[p.y][p.x]!=null){
			System.out.println("!e:DungeonMap.addRoomDir():room already exitsts at MapPosition "+p.x+","+p.y);
			return p;
		}
		rooms[p.y][p.x]=room;
		return p;
	}
	
	public Room getRoom(MapPosition p){
		if(isOutOfBounds(p)){
			System.out.println("e:DungeonMap.getRoom():out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x];
	}
	public Room getRoomDir(MapPosition MapPosition,DIR d){
		//without creating a new MapPosition object, java translates the MapPosition that is passed in call, 
		//which disrupts the move() method in the Player class
		MapPosition p=new MapPosition(MapPosition.x,MapPosition.y); 
		switch(d){
		case NORTH:
			p=p.getNorth();
			break;
		case SOUTH:
			p=p.getSouth();
			break;
		case EAST:
			p=p.getEast();
			break;
		case WEST:
			p=p.getWest();
			break;
		default:
			break;
		}
		if(isOutOfBounds(p)){
			System.out.println("!e:DungeonMap.addRoomDir():out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x];
	}
	public boolean isOutOfBounds(MapPosition p){
		return p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1;
	}
}
class  Maze5x5 extends DungeonMap{
	public Maze5x5(){
		super(5,5);
		new3x3();
	}
	public void new3x3(){
		MapPosition buildPosition=new MapPosition();
		
		Room room00=new Room();
		Weapon sword=new Weapon("Sword",2,false);
		room00.addItem(sword);
		Shield shield=new Shield("shield",4);
		room00.addItem(shield);
		Artifact ring=new Artifact("Ring",0,5,1,1);
		room00.addItem(ring);
		Potion potion=new Potion("Health Elixer",10);
		room00.addItem(potion);
		addRoom(room00, buildPosition);
		
		Room room01=new Room();
		Weapon twoHandedSword=new Weapon("2-Handed Sword",5,true);
		room01.addItem(twoHandedSword);
		Armour breastPlate=new Armour("Breast Plate",2);
		room01.addItem(breastPlate);
		Monster dragon=new Monster("Dragon",8,3,2);
		room01.setMonster(dragon);
		buildPosition.translate(DIR.SOUTH);
		addRoom(room01, buildPosition);
		
		Room room11=new Room();
		Artifact ring2=new Artifact("Ring",0,5,1,1);
		room11.addItem(ring2);
		room11.addItem(potion);
		buildPosition.translate(DIR.EAST);
		addRoom(room11, buildPosition);
		addRoom(buildPosition,DIR.EAST);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.SOUTH);
		addRoom(buildPosition,DIR.EAST);
		addRoom(buildPosition,DIR.EAST);
		
	
	}
	public void addRoom(MapPosition startPos,DIR dir){
		Room room=new Room();
		startPos.translate(dir);
		addRoom(room, startPos);
	}
}
