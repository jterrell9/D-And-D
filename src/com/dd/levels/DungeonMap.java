package com.dd.levels;

import java.lang.IllegalArgumentException;

import com.dd.entities.monsters.Dragon;
import com.dd.items.Artifact;
import com.dd.items.OneHandedWeapon;
import com.dd.items.Potion;
import com.dd.items.Shield;
import com.dd.items.Suit;

public class DungeonMap {

	private Room[][] rooms;
	private int maxRow;
	private int maxCol;
	
	public DungeonMap(int maxCol, int maxRow) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		rooms = new Room[maxRow][maxCol];
	}
	
	public DungeonMap() {
		rooms = new Room[5][5];
		OneHandedWeapon sword = new OneHandedWeapon("Sword of Mourning", 2);
		Shield shield = new Shield("Wooden Shield", 4);
		Artifact ring = new Artifact("Jade Ring", 0, 5, 1, 1);
		Potion potion = new Potion("Health Elixer", 10);
		Suit breastPlate = new Suit("Brass Breast Plate", 2);
		
		Dragon dragon = new Dragon("Dragon", 10, 5, 5);
		
		MapPosition buildPos = new MapPosition();
		addRoom(new Room(), buildPos);
		getRoom(buildPos).addItem(sword);
		getRoom(buildPos).addItem(shield);
		buildPos.moveEast();
		addRoom(new Room(), buildPos);
		getRoom(buildPos).addItem(breastPlate);
		getRoom(buildPos).addItem(ring);
		getRoom(buildPos).addItem(potion);
		getRoom(buildPos).addMonster(dragon);
		buildPos.moveEast();
		addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		addRoom(new Room(), buildPos);
		buildPos.moveEast();
		addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		addRoom(new Room(), buildPos);
		buildPos.moveEast();
		addRoom(new Room(), buildPos);
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
	
	public boolean isRoomInDir(MapPosition p, Direction direction) {
		boolean retCode = true;
		MapPosition mp;
		switch(direction) {
		case NORTH:
			mp = p.translate(Direction.NORTH);
			break;
		case SOUTH:
			mp = p.translate(Direction.SOUTH);
			break;
		case EAST:
			mp = p.translate(Direction.EAST);
			break;
		case WEST:
			mp = p.translate(Direction.WEST);
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
