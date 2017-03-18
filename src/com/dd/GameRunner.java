package com.dd;

import java.util.Scanner;

import com.dd.entities.Player;

public class GameRunner {
	public static GameState game;
	
	public static void main(String[] args){
		lnBreak("Dungeons and D&D",32);
		go();
	}
	public static void go(){
		mainMenu();
		printStats();
		cmdLoop();
	
	}
	public static void cmdLoop() {
		Command parser=new Command();
		while(true){
			parser.enterCommand();
			System.out.println();
			printStats();
		}
	}
	public static void mainMenu(){
		System.out.println("Please select from the following:"
				+"\n1: New Game"
				+"\n2: Resume Game"
				+"\n3: Quit");
		System.out.print("Enter Selection >>");
		Scanner scan=new Scanner(System.in);
		int selection=scan.nextInt();
		String name;
		if(selection==1){		//new game
			System.out.print("Enter Player's Name: ");
			name=scan.next();
			game=new GameState(new Player(name));
		}else if(selection==2){		//load game
			System.out.print("Enter Player's Name: ");
			name=scan.nextLine();
			//playerSelect=loadPlayer(name);
			///map=loadMap(name);
			//map=playerSelect.getMap();
			//map=newMap();
			//playerSelect.setMap(map);
		}else if(selection==3){		//quit
			System.out.println("Thank you for playing! GoodBye!");
			System.exit(0);
		}else{
			System.out.println("Invalid entry, please try again.");
			mainMenu();
		}
	}
	
	public static void printStats(){
		lnBreak(game.player.name+"'s Stats Board",40);
		System.out.println(game.player.statboardToString());
		lnBreak("Map",40);
		game.dungeon.drawDungeon(game.player);
		lnBreak("",40);
	}
	public static void lnBreak(String str,int width){
		int strLength=str.length();
		int startIndex=(width/2)-(strLength/2);
		for(int i=0;i<=width;i++){
			if(i==startIndex){
				System.out.print(str);
				i+=strLength;
			}else{
				System.out.print("-");
			}
		}
		System.out.println();
	}
	public static void ErrorMsg(String classLocation,String method,String msg){
		System.out.println(Command.COLOR_RED+"!e:"+classLocation+"."+method+"-"+msg+Command.COLOR_RESET);
	}
}
