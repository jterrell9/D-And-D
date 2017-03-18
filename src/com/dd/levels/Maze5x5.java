package com.dd.levels;

import com.dd.DIR;
import com.dd.entities.Monster;
import com.dd.items.Armour;
import com.dd.items.Artifact;
import com.dd.items.Potion;
import com.dd.items.Shield;
import com.dd.items.Weapon;

public class  Maze5x5 extends DungeonMap{
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
