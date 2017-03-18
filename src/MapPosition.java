
public class MapPosition {
	int col;
	int row;
	public int x;
	public int y;
	public MapPosition(){
		y=row=0;
		x=col=0;
	}
	public MapPosition(int xPos,int yPos){
		y=row=yPos;
		x=col=xPos;
	}
	public MapPosition getPosition(DIR direction){
		switch(direction){
		case NORTH:
			return new MapPosition(x,y--);
		case SOUTH:
			return new MapPosition(x,y++);
		case EAST:
			return new MapPosition(x++,y);
		case WEST:
			return new MapPosition(x--,y);
		default:
			return null;
		}
	}
	public MapPosition getNorth(){
		return new MapPosition(x,y--);
	}public MapPosition getSouth(){
		return new MapPosition(x,y++);
	}public MapPosition getEast(){
		return new MapPosition(x++,y);
	}public MapPosition getWest(){
		return new MapPosition(x--,y);
	}
	public void translate(DIR direction){
		switch(direction){
		case NORTH:
			y--;
			break;
		case SOUTH:
			y++;
			break;
		case EAST:
			x++;
			break;
		case WEST:
			x--;
			break;
		default:
			break;
		}
	}
}
