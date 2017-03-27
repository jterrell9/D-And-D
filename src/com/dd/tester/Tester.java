package com.dd.tester;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dd.Console;
import com.dd.GameRunner;
import com.dd.GameState;
import com.dd.entities.Player;
import com.dd.entities.monsters.*;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;
import com.dd.items.*;

public class Tester {
	
	public static void go() throws FileNotFoundException {
		System.out.println("\nWelcome to Dungeons and D & D!");
		mainMenu();
		Console.updateScreen(Console.activeRoom().examineString());
		System.out.println();
		Console.cmdLoop();
	}
	
	public static void mainMenu() throws FileNotFoundException{
		System.out.println();
		Console.printLnTitle('*', "Main Menu", 40);
		System.out.println(
				"1: New Game"
				+ "\n2: Resume Game"
				+ "\n3: Quit");
		System.out.print("\nEnter Selection >> ");
		try{
			Scanner scanInt = new Scanner(System.in);
			int selection = scanInt.nextInt();
			String name;
			if(selection == 1){		//new game
				Scanner scanName = new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.next();
				GameState game = new GameState(name, new Player(name), new DungeonMap(5,5));
				GameRunner.registerGameState(game);
				GameRunner.setActiveGameState(game);
				populate5x5();
			}
			else if(selection == 2){		//load game *NOT FUNCTIONING
				Scanner scanName = new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name = scanName.nextLine();
				//logic goes here to deserialize using GSON
				//game.setMap(loadMap(name));
				//game.addActivePlayer(loadPlayer(name));
			}
			else if(selection == 3){		//quit
				System.out.println("\nThank you for playing! GoodBye!\n");
				System.exit(0);
			}
			else{
				System.out.println("\n!e:Invalid entry, please try again.\n");
				mainMenu();
			}
		}catch(InputMismatchException ime){
			System.out.println("\n!e:Invalid entry, please try again.\n");
			mainMenu();
		}
	}
	
	public static void populate5x5(){
		OneHandedWeapon sword = new OneHandedWeapon("Sword of Mourning", 2);
		Shield shield = new Shield("Wooden Shield", 4);
		Artifact ring = new Artifact("Jade Ring", 0, 5, 1, 1);
		Potion potion = new Potion("Health Elixer", 10);
		Suit breastPlate = new Suit("Brass Breast Plate", 2);
		
		Dragon dragon = new Dragon("Dragon", 10, 5, 5);
		
		MapPosition buildPos = new MapPosition();
		Console.activeMap().addRoom(new Room(), buildPos);
		Console.activeMap().getRoom(buildPos).addItem(sword);
		Console.activeMap().getRoom(buildPos).addItem(shield);
		buildPos.moveEast();
		Console.activeMap().addRoom(new Room(), buildPos);
		Console.activeMap().getRoom(buildPos).addItem(breastPlate);
		Console.activeMap().getRoom(buildPos).addItem(ring);
		Console.activeMap().getRoom(buildPos).addItem(potion);
		Console.activeMap().getRoom(buildPos).addMonster(dragon);
		buildPos.moveEast();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveEast();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveSouth();
		Console.activeMap().addRoom(new Room(), buildPos);
		buildPos.moveEast();
		Console.activeMap().addRoom(new Room(), buildPos);
	}
}
