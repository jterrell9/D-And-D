import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class Command {
	
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	public static final String COLOR_GREEN = "\u001B[32m";
	public static final String COLOR_YELLOW = "\u001B[33m";
	public static final String COLOR_BLUE = "\u001B[34m";
	public static final String COLOR_PURPLE = "\u001B[35m";
	public static final String COLOR_CYAN = "\u001B[36m";
	
	GameState active;
	String cmd=new String();
	String opt=new String();
	String[] opts=new String[10];
	int optNum;
	
	int itemIndex;
	
	public Command(){
		active=GameRunner.game;
	}
	
	public void enterCommand(){
		promptParse();
		mapCmd();
	}
	
	public void promptParse(){
		Scanner user=new Scanner(System.in);
		System.out.print(COLOR_BLUE+active.player.name+">> "+COLOR_RESET);
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
			save();
			GameRunner.mainMenu();
			return;
		
		case "attack":
			
			return;
			
		case "move":
			if(opts[0]!=null){
				switch(opts[0]){
				case "north": 
					active.player.move(DIR.NORTH);
					active.player.getRoom().examine();
					return;
				case "south": 
					active.player.move(DIR.SOUTH);
					active.player.getRoom().examine();
					return;
				case "east": 
					active.player.move(DIR.EAST);
					active.player.getRoom().examine();
					return;
				case "west": 
					active.player.move(DIR.WEST);
					active.player.getRoom().examine();
					return;
				}
			}
			System.out.println("ERROR type north, south, east, or west to move");
			return;
				
		case "examine":
			switch(opts[0]){
			case "room":
				active.player.getRoom().examine();
				return;
			case "monster":
				active.player.getRoom().getMonster().examine();
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
				item=active.player.getRoom().getItem(itemIndex);
				if(item instanceof Potion){
					System.out.println("ERROR cannot equip a potion. You must pickup a potion");
					return;
				}
				active.player.equip(item);
				active.player.getRoom().examine();
			}
			return;	
				
		case "drop":
			if(opt!=null){
				if(opts[0].equals("inventory")){
					if(optNum<0){
						return;
					}
					active.player.drop(active.player.getInventoryItem(itemIndex));
					active.player.getRoom().examine();
					return;
				}
			}
				
			switch(opt){
			
			case "left hand":
				active.player.drop(Player.EQUIP.LEFTHAND);
				active.player.getRoom().examine();
				return;
			case "right hand":
				active.player.drop(Player.EQUIP.RIGHTHAND);
				active.player.getRoom().examine();
				return;
			case "hands":
				active.player.drop(Player.EQUIP.HANDS);
				active.player.getRoom().examine();
				return; 
			case "suit":
				active.player.drop(Player.EQUIP.SUIT);
				active.player.getRoom().examine();
				return;
			}
			System.out.println("ERROR enter 'drop followed by 'left hand','right hand','hands','suit', or 'inventory #'");
			return;
				
		case "pickup":	
			if(optNum<0){
				return;
			}
			item=active.player.getRoom().getItem(itemIndex);
			if(item==null){
				return;
			}
			if(!(item instanceof Potion)){
				System.out.println("ERROR you can only pickup potions");
				return;
			}
			active.player.pickup(item);
			active.player.getRoom().examine();
			return;
		
		case "use":
			switch(opts[0]){
			case "inventory":
				if(optNum<0){
					return;
				}
				item=active.player.getInventoryItem(itemIndex);
				active.player.usePotionFromInv(item);
				return;
			case "item":
				if(optNum<0){
					return;
				}
				item=active.player.getRoom().getItem(itemIndex);
				active.player.usePotion(item);
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
			File gsonPlayerFile=new File(active.player.name+".json");
			PrintStream toGsonPlayerFile=new PrintStream(gsonPlayerFile);
			toGsonPlayerFile.println(new Gson().toJson(active.player));
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
