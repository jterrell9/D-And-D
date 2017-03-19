package com.dd;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dd.entities.Player;
import com.dd.levels.Maze5x5;

public class GameRunner {
	public static GameState game;
	
	public static void main(String[] args) throws FileNotFoundException{
		printLnTitle('~',"Dungeons and D&D",40);
		go();
	}
	public static void go() throws FileNotFoundException{
		mainMenu();
		printStats();
		printMap();
		System.out.println("*Type help for a list of commands*");
		cmdLoop();
	
	}
	public static void cmdLoop() throws FileNotFoundException {
		Command parser=new Command();
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
				GameState.dungeon=new Maze5x5();
				GameState.player=new Player(name);
			}else if(selection==2){		//load game
				Scanner scanName=new Scanner(System.in);
				System.out.print("Enter Player's Name: ");
				name=scanName.nextLine();
				GameState.dungeon=GameState.loadMap(name);
				GameState.player=GameState.loadPlayer(name);
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
		printLnTitle('~',GameState.player.name+"'s Stats Board",40);
		System.out.println(GameState.player.statboardToString());
	}
	public static void printMap(){
		printLnTitle('-',"Map",40);
		GameState.dungeon.drawDungeon(GameState.player);
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
	public static void ErrorMsg(String classLocation,String method,String msg){
		System.out.println("!e:"+classLocation+"."+method+"-"+msg);
	}
}
