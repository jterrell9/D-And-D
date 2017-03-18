package old;
import java.awt.Point;

public class Map {
	public enum DIR{
		NORTH,SOUTH,EAST,WEST
	}
	
	private transient Room[][] rooms;
	private int maxRow;
	private int maxCol;
	public Map(int maxCol,int maxRow){
		this.maxRow=maxRow;
		this.maxCol=maxCol;
		rooms=new Room[maxRow][maxCol];
	}
	public Map(){
		super();
	}
	
	//public 
	
	public Point addRoom(Room r,Point p){
		if(isOutOfBounds(p)){
			System.out.println("ERROR out of bounds of map");
			return p;
		}
		if(rooms[p.y][p.x]!=null){
			System.out.println("ERROR room already exitsts at point "+p.x+","+p.y);
			return p;
		}
		rooms[p.y][p.x]=r;
		return p;
	}
	public Point addRoomDir(Room r,Point p,DIR d){
		switch(d){
		case NORTH:
			p.translate(0,-1);
			break;
		case SOUTH:
			p.translate(0,1);
			break;
		case EAST:
			p.translate(1,0);
			break;
		case WEST:
			p.translate(-1,0);
			break;
		}
		if(isOutOfBounds(p)){
			System.out.println("ERROR out of bounds of map");
			return p;
		}
		if(rooms[p.y][p.x]!=null){
			System.out.println("ERROR room already exitsts at point "+p.x+","+p.y);
			return p;
		}
		rooms[p.y][p.x]=r;
		return p;
	}
	
	public Room getRoom(Point p){
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x];
	}
	public Room getRoomDir(Point position,DIR d){
		//without creating a new Point object, java translates the Point that is passed in call, 
		//which disrupts the move() method in the Player class
		Point p=new Point(position.x,position.y); 
		switch(d){
		case NORTH:
			p.translate(0,-1);
			break;
		case SOUTH:
			p.translate(0,1);
			break;
		case EAST:
			p.translate(1,0);
			break;
		case WEST:
			p.translate(-1,0);
			break;
		}
		if(isOutOfBounds(p)){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x];
	}
	
	public void drawPlayer(Player p){
		Point pos=p.getPostion();
		for(int y=0;y<maxRow;y++){
			for(int x=0;x<maxCol;x++){
				if(pos.x==x && pos.y==y)
					System.out.print("#");
				else if(rooms[y][x]!=null)
					System.out.print("X");
				else
					System.out.print("0");
			}
			System.out.println();
		}
	}
	public Room[][] getRooms(){
		return rooms;
	}
	public void setRooms(Room[][] setRooms){
		this.rooms=setRooms;
	}
	public boolean isOutOfBounds(Point p){
		return p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1;
	}
}
