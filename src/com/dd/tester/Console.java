package com.dd.tester;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.dd.command_util.CommandHandler.CommandHandlerException;
import com.dd.DandD;
import com.dd.command_util.CommandParser;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Room;

public class Console {
	
	public static void prompt() throws FileNotFoundException, CommandHandlerException{
		Console.printLnTitle('~', "CONSOLE INPUT", 40);
		Scanner user = new Scanner(System.in);
		System.out.print(Console.activePlayer().getName() + ">> ");
		String userInput = user.nextLine();
		CommandParser parser = new CommandParser();
		parser.parse(userInput);
	}
	
	public static void updateScreen(String logOutput) {
		printStats();
		printMap();
		Console.printLnTitle('~', "LOG", 40);
    	System.out.println(logOutput);
    }
	
	public static Player activePlayer(){
		return DandD.getActiveGameState().getActivePlayer();
	}
	
	public static DungeonMap activeMap(){
		return DandD.getActiveGameState().getMap();
	}
	
	public static MapPosition activePos(){
		return activePlayer().getPostion();
	}
	
	public static Room activeRoom(){
		return activeMap().getRoom(activePos());
	}
	
	public static void printStats() {
		printLnTitle('~', activePlayer().getName().toUpperCase() + "'S STATS BOARD", 40);
		System.out.println(activePlayer().statboardToString());
	}
	
	public static void printMap() {
		printLnTitle('~', "MAP", 40);
		MapPosition playerPos = activePlayer().getPostion();
		for(int y = 0; y < activeMap().getMaxRow(); y++){
			for(int x = 0; x < activeMap().getMaxCol(); x++){
				if(x == 0)
					System.out.print("\t|");
				if(playerPos.getX() == x && playerPos.getY() == y)
					System.out.print("#");
				else if(activeMap().isRoom(new MapPosition(x, y)))
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.print("|\n");
		}
	}
	
    public static void printLnTitle(char c, String str, int width) {
		int strLength = str.length();
		int startIndex = (width / 2) - (strLength / 2);
		for(int i = 0; i <= width; i++){
			if(i == startIndex){
				System.out.print(str);
				i += strLength;
			}else{
				System.out.print(c);
			}
		}
		System.out.println();
	}
}
