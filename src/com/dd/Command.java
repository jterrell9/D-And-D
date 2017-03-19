package com.dd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.items.Potion;
import com.google.gson.Gson;

public class Command {
	
	String cmd=new String();
	String opt=new String();
	String[] opts=new String[10];
	int optNum;
	
	public Command(){
		super();
	}
	
	public void enterCommand(){
		promptParse();
		mapCmd();
	}
	
	public void promptParse(){
		Scanner user=new Scanner(System.in);
		System.out.print(GameState.player.name+">> ");
		String userInput=user.nextLine();
		String[] input=userInput.toLowerCase().split(" ");
		cmd=input[0];
		if(input.length>1 && input.length<11){
			for(int i=1;i<input.length;i++){
				opt+=input[i]+" ";
				opts[i-1]=input[i];
				if(isInteger(input[i])){
					optNum=Integer.parseInt(input[i]);
				}
				
			}
		}
	}
	
	public void mapCmd() {
		Item item;
		
		switch(cmd){
		
		case "q":
			System.exit(0);
			return;
		
		case "menu":
			GameRunner.mainMenu();
			return;
		
		case "save":
			GameState.save();
			GameRunner.mainMenu();
			return;
		
		case "attack":
			
			return;
			
		case "move":
			if(opts[0]!=null){
				switch(opts[0]){
				case "north": 
					GameState.player.move(DIR.NORTH);
					GameState.player.getRoom().examine();
					return;
				case "south": 
					GameState.player.move(DIR.SOUTH);
					GameState.player.getRoom().examine();
					return;
				case "east": 
					GameState.player.move(DIR.EAST);
					GameState.player.getRoom().examine();
					return;
				case "west": 
					GameState.player.move(DIR.WEST);
					GameState.player.getRoom().examine();
					return;
				}
			}
			System.out.println("ERROR type north, south, east, or west to move");
			return;
				
		case "examine":
			switch(opts[0]){
			case "room":
				GameState.player.getRoom().examine();
				return;
			case "monster":
				GameState.player.getRoom().getMonster().examine();
				return;
			default:
				break;
			}
			System.out.println("ERROR enter room, object, or monster to examine");
			return;
				
		case "equip":
			if(opts[0].equals("item")){
				if(optNum<0){
					return;
				}
				item=GameState.player.getRoom().getItem(optNum);
				if(item instanceof Potion){
					System.out.println("ERROR cannot equip a potion. You must pickup a potion");
					return;
				}
				GameState.player.equip(item);
				GameState.player.getRoom().examine();
			}
			else{
				System.out.println("e:Command(equip):not followed by 'item'");
			}
			return;	
				
		case "drop":
			if(opt!=null){
				if(opts[0].equals("inventory")){
					if(optNum<0){
						return;
					}
					GameState.player.drop(GameState.player.getInventoryItem(optNum));
					GameState.player.getRoom().examine();
					return;
				}
			}
				
			switch(opt){
			
			case "left hand":
				GameState.player.drop(Player.EQUIP.LEFTHAND);
				GameState.player.getRoom().examine();
				return;
			case "right hand":
				GameState.player.drop(Player.EQUIP.RIGHTHAND);
				GameState.player.getRoom().examine();
				return;
			case "hands":
				GameState.player.drop(Player.EQUIP.HANDS);
				GameState.player.getRoom().examine();
				return; 
			case "suit":
				GameState.player.drop(Player.EQUIP.SUIT);
				GameState.player.getRoom().examine();
				return;
			}
			System.out.println("ERROR enter 'drop followed by 'left hand','right hand','hands','suit', or 'inventory #'");
			return;
				
		case "pickup":	
			if(optNum<0){
				return;
			}
			item=GameState.player.getRoom().getItem(optNum);
			if(item==null){
				return;
			}
			if(!(item instanceof Potion)){
				System.out.println("ERROR you can only pickup potions");
				return;
			}
			GameState.player.pickup(item);
			GameState.player.getRoom().examine();
			return;
		
		case "use":
			switch(opts[0]){
			case "inventory":
				if(optNum<0){
					return;
				}
				item=GameState.player.getInventoryItem(optNum);
				GameState.player.usePotionFromInv(item);
				return;
			case "item":
				if(optNum<0){
					return;
				}
				item=GameState.player.getRoom().getItem(optNum);
				GameState.player.usePotion(item);
				return;
			}
			return;
		default:
			System.out.println("ERROR command not recognized");
			break;
		}
	}
	
	public void save(){
		try{
			File gsonPlayerFile=new File(GameState.player.name+".json");
			PrintStream toGsonPlayerFile=new PrintStream(gsonPlayerFile);
			toGsonPlayerFile.println(new Gson().toJson(GameState.player));
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
	public boolean isInteger(String str) {
	      try {
	         Integer.parseInt(str);
	         return true;
	      }catch (NumberFormatException ex){
	    	  return false;
	      }
	   }
	
	@Override
	public String toString(){
		return "Command:\t"+cmd+"\n"+
		"Option:\t\t"+opt;
	}	
}
