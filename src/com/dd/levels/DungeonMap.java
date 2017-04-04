package com.dd.levels;

import java.lang.IllegalArgumentException;
import java.util.Random;

import com.dd.entities.Equip;
import com.dd.entities.monsters.Dragon;
import com.dd.items.*;

public class DungeonMap {

	private Room[][] rooms;
	private int maxRow = 10;
	private int maxCol = 10;
	private Random rand;
	private final String[] dragNames = {
			"Thordak",
			"Raishan",
			"Galisha",
			"Velica",
			"Vorical",
			"Dairak",
			"Kelshawn",
			"Herp",
			"Saphera",
			"Puff",
			"Shenron",
			"Smaug",
			"Vizarion",
			"Valoo",
			"Faizon"
	};
	private final String[] gobNames = {
			"Bogoblin",
			"Rowllin",
			"Stellart",
			"Combarn",
			"Meltion",
			"Ori",
			"Romil"
	};
	private final String[] beholdNames = {
			"K'varn",
			"Re'tunar",
			"Ei'Soma",
			"Emo'Sutra",
			"Brah'Zumar",
			"Ko'Rita",
			"Tumo'Uta",
			"Ai'Mantra",
			"Ma'Kefet",
			"Zi'Tumo"
	};

	public DungeonMap() {
		rooms = new Room[5][5];
		OneHandedWeapon sword = new OneHandedWeapon("Sword of Mourning", 2);
		TwoHandedWeapon twoHandedSword = new TwoHandedWeapon("Two Handed Sword", 5);
		Magical wand = new Magical("Wand", Equip.HANDS, 0, 2, 4, 2);
		Shield shield = new Shield("Wooden Shield", 4);
		Artifact ring = new Artifact("Jade Ring", 0, 5, 1, 1);
		Potion potion = new Potion("Health Elixer", 10);
		Suit breastPlate = new Suit("Brass Breast Plate", 2);

		Dragon dragon = new Dragon("Dragon", 10, 5, 5);

		MapPosition buildPos = new MapPosition();
		addRoom(new Room(), buildPos);
		getRoom(buildPos).addItem(sword);
		getRoom(buildPos).addItem(shield);
		getRoom(buildPos).addItem(wand);
		getRoom(buildPos).addItem(twoHandedSword);
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
	
	public DungeonMap(int seed) {
		rand = new Random(seed);
		rooms = new Room[maxRow][maxCol];
		generateDungeon();
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

	public void generateDungeon() {
		Room start = new Room();
		start.addItem(new OneHandedWeapon("wooden sword", 2));
		start.addItem(new Shield("wooden shield", 1));
		int yStart = rand.nextInt(10);
		int xStart = rand.nextInt(10);
		rooms[yStart][xStart] = start;
		Room end = new Room();

		end.addMonster(new Dragon(dragNames[rand.nextInt(15)], 40, 10, 10));
		int yEnd = rand.nextInt(10);
		while(yEnd <= yStart + 3
				|| yEnd >= yStart - 3
				|| yEnd <= yEnd - 3
				|| yEnd >= yStart + 3) {
			yEnd = rand.nextInt(10);
		}
		int xEnd = rand.nextInt(10);
		while(xEnd <= xStart + 3
				|| xEnd >= xStart - 3
				|| xEnd > xStart + 3
				|| xEnd <= xStart - 3) {
			xEnd = rand.nextInt(10);
		}
		rooms[yEnd][xEnd] = end;

	}
}
