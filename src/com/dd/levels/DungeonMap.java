package com.dd.levels;

import java.lang.IllegalArgumentException;

public class DungeonMap {

	private Room[][] rooms;
	private int maxRow;
	private int maxCol;
	
	public DungeonMap(int maxCol, int maxRow) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		rooms = new Room[maxRow][maxCol];
	}

	public int getMaxRow() {
		return maxRow;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public boolean isRoom(MapPosition p) {
		boolean retCode = true;
		if(isOutOfBounds(p) || rooms[p.getY()][p.getX()]==null) {
			retCode = false;
		}
		return retCode;
	}
	
	public boolean isRoomInDir(MapPosition p, DIR direction) {
		boolean retCode = true;
		MapPosition mp;
		switch(direction) {
		case NORTH:
			mp = p.translate(DIR.NORTH);
			break;
		case SOUTH:
			mp = p.translate(DIR.SOUTH);
			break;
		case EAST:
			mp = p.translate(DIR.EAST);
			break;
		case WEST:
			mp = p.translate(DIR.WEST);
			break;
		default:
			mp = p;
		}
		if(isOutOfBounds(mp) || rooms[mp.getY()][mp.getX()] == null) {
			retCode = false;
		}
		return retCode;
	}
	
	public void addRoom(Room room, MapPosition position) {
		if(isOutOfBounds(position)){
			throw new IllegalArgumentException("The position ("
												+ Integer.toString(position.getX())
												+ ", "
												+ Integer.toString(position.getY())
												+") is out of bounds of the map. Room not added to DungeonMap.");
		}
		else if(rooms[position.getY()][position.getX()] != null) {
			throw new IllegalArgumentException("The position ("
												+ Integer.toString(position.getX())
												+ ", "
												+ Integer.toString(position.getY())
												+ ") already contains a room. Room not added to DungeonMap.");
		}
		rooms[position.getY()][position.getX()] = room;
	}
	
	public Room getRoom(MapPosition pos) {
		if(isOutOfBounds(pos)) {
			System.out.println("e:DungeonMap.getRoom():out of bounds of map");
			return null;
		}
		return rooms[pos.getY()][pos.getX()];
	}

	public boolean isOutOfBounds(MapPosition pos) {
		return pos.getX() < 0
				|| pos.getX() > rooms.length - 1
				|| pos.getY() < 0
				|| pos.getY() > rooms[pos.getY()].length - 1;
	}
}
