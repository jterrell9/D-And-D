package com.dd.tester;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dd.GameState;
import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.items.Armour;
import com.dd.items.Artifact;
import com.dd.items.OneHandedWeapon;
import com.dd.items.Potion;
import com.dd.items.Shield;
import com.dd.items.TwoHandedWeapon;
import com.dd.items.Weapon;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public class Tester {
	
	private static GameState game;
	public static void go() throws FileNotFoundException{
		
		mainMenu();
		printStats();
		printMap();
		System.out.println("*Type help for a list of commands*");
		cmdLoop();
	
	}
	public static void cmdLoop() throws FileNotFoundException {
		Command parser=new Command(game);
		while(true){
			parser.enterCommand();
			System.out.println();
			printStats();
			printMap();
		}
	}
	public static void mainMenu() throws FileNotFoundException{
		printLnTitle('*',"Main Menu",40);
		System.out.println("Please select from the following:"
				+"\n1: New Game"
				+"\n2: Resume Game"
				+"\n3: Quit");
		System.out.print("Enter Selection >>");
		try{
			Scanner scanInt=new Scanner(System.in);
			int selection=scanInt.nextInt();
			String name;
			if(selection==1){		//new game
				Scanner scanName=new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.next();
				game.setMap(new5x5());
				game.addActivePlayer(new Player(name));
			}else if(selection==2){		//load game
				Scanner scanName=new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.nextLine();
				//game.setMap(loadMap(name));
				//game.addActivePlayer(loadPlayer(name));
			}else if(selection==3){		//quit
				System.out.println("Thank you for playing! GoodBye!");
				System.exit(0);
			}else{
				System.out.println("\n!e:Invalid entry, please try again.\n");
				mainMenu();
			}
		}catch(InputMismatchException ime){
			System.out.println("\n!e:Invalid entry, please try again.\n");
			mainMenu();
		}
	}
	
	public static void printStats(){
		printLnTitle('~',game.getActivePlayer().getName()+"'s Stats Board",40);
		System.out.println(game.getActivePlayer().statboardToString());
	}
	public static void printMap(){
		printLnTitle('-',"Map",40);
		drawDungeon();
		printLnTitle('-',"",40);
	}
	public static void printLnTitle(char c,String str,int width){
		int strLength=str.length();
		int startIndex=(width/2)-(strLength/2);
		for(int i=0;i<=width;i++){
			if(i==startIndex){
				System.out.print(str);
				i+=strLength;
			}else{
				System.out.print(c);
			}
		}
		System.out.println();
	}
	public static DungeonMap new5x5(){
		DungeonMap maze=new DungeonMap(5,5);
		MapPosition buildPosition=new MapPosition();
		
		Room room00=new Room();
		OneHandedWeapon sword=new OneHandedWeapon("Sword",2);
		room00.addItem(sword);
		Shield shield=new Shield("shield",4);
		room00.addItem(shield);
		Artifact ring=new Artifact("Ring",0,5,1,1);
		room00.addItem(ring);
		Potion potion=new Potion("Health Elixer",10);
		room00.addItem(potion);
		maze.addRoom(room00, buildPosition);
		
		Room room01=new Room();
		TwoHandedWeapon twoHandedSword=new TwoHandedWeapon("2-Handed Sword",5);
		room01.addItem(twoHandedSword);
		Armour breastPlate=new Armour("Breast Plate",2);
		room01.addItem(breastPlate);
		Monster dragon=new Monster("Dragon",8,3,2);
		room01.addMonster(dragon);
		buildPosition.translate(DIR.SOUTH);
		maze.addRoom(room01, buildPosition);
		
		Room room11=new Room();
		Artifact ring2=new Artifact("Ring",0,5,1,1);
		room11.addItem(ring2);
		room11.addItem(potion);
		buildPosition.translate(DIR.EAST);
		maze.addRoom(room11, buildPosition);
		addRoom(maze,buildPosition,DIR.EAST);
		addRoom(maze,buildPosition,DIR.SOUTH);
		addRoom(maze,buildPosition,DIR.SOUTH);
		addRoom(maze,buildPosition,DIR.SOUTH);
		addRoom(maze,buildPosition,DIR.EAST);
		addRoom(maze,buildPosition,DIR.EAST);
		
		return maze;
	}
	public static void addRoom(DungeonMap map,MapPosition startPos,DIR dir){
		Room room=new Room();
		startPos.translate(dir);
		map.addRoom(room, startPos);
	}
	public static void drawDungeon(){
		MapPosition playerPos=game.getActivePlayer().getPostion();
		DungeonMap map=game.getMap();
		for(int y=0;y<map.getMaxRow();y++){
			for(int x=0;x<map.getMaxCol();x++){
				if(x==0)
					System.out.print("\t|");
				if(playerPos.getX()==x && playerPos.getY()==y)
					System.out.print("#");
				else if(map.isRoom(new MapPosition(x,y)))
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.print("|\n");
		}
	}

}
