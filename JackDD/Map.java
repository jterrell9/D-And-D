import java.awt.Point;

public class Map {
	private Room[][] rooms;
	private int maxRow;
	private int maxCol;
	public Map(int maxCol,int maxRow){
		this.maxRow=maxRow;
		this.maxCol=maxCol;
		rooms=new Room[maxRow][maxCol];
	}
	
	
	public Point addRoom(Room r,Point p){
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
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
	public Point addRoomNorth(Room r,Point p){
		p.translate(0,-1);
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
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
	public Point addRoomSouth(Room r,Point p){
		p.translate(0,1);
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
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
	public Point addRoomEast(Room r,Point p){
		p.translate(1,0);
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
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
	public Point addRoomWest(Room r,Point p){
		p.translate(-1,0);
		if(p.x<0 || p.x>rooms.length-1 || p.y<0 || p.y>rooms[p.x].length-1){
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
	
	public Room getNorth(Point p){
		if(p.x<0 || p.x>rooms.length || p.y-1<0 || p.y-1>rooms[p.x].length){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y-1][p.x];
	}
	public Room getSouth(Point p){
		if(p.x<0 || p.x>rooms.length || p.y+1<0 || p.y+1>rooms[p.x].length){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y+1][p.x];
	}
	public Room getEast(Point p){
		if(p.x+1<0 || p.x+1>rooms.length || p.y<0 || p.y>rooms[p.x].length){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x+1];
	}
	public Room getWest(Point p){
		if(p.x-1<0 || p.x-1>rooms.length || p.y<0 || p.y>rooms[p.x].length){
			System.out.println("ERROR out of bounds of map");
			return null;
		}
		return rooms[p.y][p.x-1];
	}
	
	public void draw(){
		for(int y=0;y<maxRow;y++){
			for(int x=0;x<maxCol;x++){
				if(rooms[y][x]!=null)
					System.out.print("X");
				else
					System.out.print("0");
			}
			System.out.println();
		}
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
}
