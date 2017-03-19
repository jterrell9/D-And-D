package com.dd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.dd.entities.Player;
import com.dd.levels.DungeonMap;
import com.dd.levels.MapPosition;
import com.dd.levels.Maze5x5;
import com.google.gson.Gson;

public class GameState {
	public static Player player;
	public static DungeonMap dungeon;
	
	public GameState(Player p1,DungeonMap dm1){
		dungeon=dm1;
		player=p1;
	}
	public GameState(Player p1){
		dungeon=new Maze5x5();
		player=p1;
	}
	public GameState(){
		dungeon=new Maze5x5();
		player=new Player("Player",new MapPosition(),new Stats());
	}
	public static void save(){
		try{
			File gsonPlayerFile=new File(player.name+".json");
			PrintStream toGsonPlayerFile=new PrintStream(gsonPlayerFile);
			toGsonPlayerFile.println(new Gson().toJson(player));
			toGsonPlayerFile.close();	
			
			/*
			File gsonMapFile=new File(active.player.getName()+".Map.json");
			PrintStream toGsonMapFile=new PrintStream(gsonMapFile);
			toGsonMapFile.println(new Gson().toJson(activeMap.getRooms()));
			toGsonMapFile.close();	
			*/
			
		}catch(FileNotFoundException FNFE){
			System.out.println("ERROR file not found");
		}
	}
}
