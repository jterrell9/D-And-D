package old;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.Gson;

public class Command {
	
	private Player activePlayer;
	private Map activeMap;
	private String cmd;
	private String opt;
	
	static PrintStream output=new PrintStream(System.out);
	Scanner user=new Scanner(System.in);
	
	public Command(Player activePlayer){
		this.activePlayer=activePlayer;
		this.activeMap=activePlayer.getMap();
		setCmd(null);
		setOpt(null);
	}
	
	public void enterCommand() throws FileNotFoundException{
		promptParse();
		mapCmd();
	}
	
	public void promptParse(){
		output.print("    "+activePlayer.getName()+" >> ");
		String userInput=user.nextLine();
		userInput=userInput.toLowerCase();
		String[] input=userInput.split(" ");
		setCmd(input[0]);
		String option=new String();;
		if(input.length>1){
			for(int i=1;i<input.length-1;i++){
				option+=input[i]+" ";
			}
			option+=input[input.length-1];
			setOpt(option);
		}
	}
	
	public void mapCmd() throws FileNotFoundException {
		int itemIndex=-1;
		Item item;
		
		switch(getCmd()){
		
		case "menu":
			Main.mainMenu();
			return;
		
		case "save":
			save();
			Main.mainMenu();
			return;
		
		case "attack":
			if(activePlayer.isinFight()){
				activePlayer.attack(activePlayer.getRoom().getMonster());
			}
			return;
			
		case "move":
			if(getOpt()!=null){
				switch(getOpt()){
				case "north": 
					activePlayer.move(Map.DIR.NORTH,activeMap);
					activePlayer.getRoom().examine();
					if(activePlayer.getRoom().hasMonster()){
						fight(activePlayer,activePlayer.getRoom().getMonster());
					}
					return;
				case "south": 
					activePlayer.move(Map.DIR.SOUTH,activeMap);
					activePlayer.getRoom().examine();
					if(activePlayer.getRoom().hasMonster()){
						fight(activePlayer,activePlayer.getRoom().getMonster());
					}
					return;
				case "east": 
					activePlayer.move(Map.DIR.EAST,activeMap);
					activePlayer.getRoom().examine();
					if(activePlayer.getRoom().hasMonster()){
						fight(activePlayer,activePlayer.getRoom().getMonster());
					}
					return;
				case "west": 
					activePlayer.move(Map.DIR.WEST,activeMap);
					activePlayer.getRoom().examine();
					if(activePlayer.getRoom().hasMonster()){
						fight(activePlayer,activePlayer.getRoom().getMonster());
					}
					return;
				}
			}
			output.println("ERROR type north, south, east, or west to move");
			return;
				
		case "examine":
			if(getOpt()!=null){
				switch(getOpt()){
				case "room":
					activePlayer.getRoom().examine();
					return;
				case "monster":
					activePlayer.getRoom().getMonster().examine();
					return;
				}
			}
			output.println("ERROR enter room, object, or monster to examine");
			return;
				
		case "equip":
			itemIndex=parseOptNum("item");
			if(itemIndex<0){
				return;
			}
			item=activePlayer.getRoom().getItem(itemIndex);
			if(item instanceof Potion){
				output.println("ERROR cannot equip a potion. You must pickup a potion");
				return;
			}
			activePlayer.equip(item);
			activePlayer.getRoom().examine();
			return;	
				
		case "drop":
			if(getOpt()!=null){
				String[] opts=getOpt().split(" ");
				if(opts[0].equals("inventory")){
					itemIndex=parseOptNum("inventory");
					if(itemIndex<0){
						return;
					}
					activePlayer.drop(activePlayer.getInventoryItem(itemIndex));
					activePlayer.getRoom().examine();
					return;
				}
			}
				
			switch(getOpt()){
			
			case "left hand":
				activePlayer.drop(Player.EQUIP.LEFTHAND);
				activePlayer.getRoom().examine();
				return;
			case "right hand":
				activePlayer.drop(Player.EQUIP.RIGHTHAND);
				activePlayer.getRoom().examine();
				return;
			case "hands":
				activePlayer.drop(Player.EQUIP.HANDS);
				activePlayer.getRoom().examine();
				return; 
			case "suit":
				activePlayer.drop(Player.EQUIP.SUIT);
				activePlayer.getRoom().examine();
				return;
			}
			output.println("ERROR enter 'drop followed by 'left hand','right hand','hands','suit', or 'inventory #'");
			return;
				
		case "pickup":	
			itemIndex=parseOptNum("item");
			if(itemIndex<0){
				return;
			}
			item=activePlayer.getRoom().getItem(itemIndex);
			if(item==null){
				return;
			}
			if(!(item instanceof Potion)){
				output.println("ERROR you can only pickup potions");
				return;
			}
			activePlayer.pickup(item);
			activePlayer.getRoom().examine();
			return;
		
		case "use":
			switch(parseOpt1()){
			case "inventory":
				itemIndex=parseOptNum("inventory");
				if(itemIndex<0){
					return;
				}
				item=activePlayer.getInventoryItem(itemIndex);
				activePlayer.usePotionFromInv(item);
				return;
			case "item":
				itemIndex=parseOptNum("item");
				if(itemIndex<0){
					return;
				}
				item=activePlayer.getRoom().getItem(itemIndex);
				activePlayer.usePotion(item);
				return;
			}
			return;
		}
		output.println("ERROR command not recognized");
		return;
	}
	
	public String parseOpt1(){
		if(getOpt()!=null){
			String opts[]=getOpt().split(" ");
			return opts[0];
		}else{
			output.println("ERROR no option present");
			return null;
		}
	}
	public int parseOptNum(String opt1){
		int num=-1;
		if(getOpt()!=null){
			if(parseOpt1().equals(opt1)){
				try{
					num=Integer.parseInt(getOpt().replaceAll("[\\D]", ""))-1;	
				}catch(NumberFormatException nfe){
					output.println("ERROR enter '"+getCmd()+" "+opt1+"', followed by the "+opt1+" number");
				}
			}else{
				output.println("ERROR enter '"+getCmd()+" "+opt1+"', followed by the "+opt1+" number");
			}
		}else{
			output.println("ERROR enter '"+getCmd()+" "+opt1+"', followed by the "+opt1+" number");
		}
		return num;
	}
	
	public void fight(Player currPlayer,Monster currMonster) throws FileNotFoundException{
		output.println("this room has a "+currPlayer.getRoom().getMonster()+"!\n"
				+ "Time to FIGHT!\n"
				+"Your Move!");
		currPlayer.fightTurn(currPlayer,currMonster);
	}
	
	public void save(){
		try{
			File gsonPlayerFile=new File(activePlayer.getName()+".json");
			PrintStream toGsonPlayerFile=new PrintStream(gsonPlayerFile);
			toGsonPlayerFile.println(new Gson().toJson(activePlayer));
			toGsonPlayerFile.close();	
			
			/*
			File gsonMapFile=new File(activePlayer.getName()+".Map.json");
			PrintStream toGsonMapFile=new PrintStream(gsonMapFile);
			toGsonMapFile.println(new Gson().toJson(activeMap.getRooms()));
			toGsonMapFile.close();	
			*/
			
		}catch(FileNotFoundException FNFE){
			output.println("ERROR file not found");
		}
	}
	
	@Override
	public String toString(){
		return "Command:\t"+getCmd()+"\n"+
		"Option:\t\t"+getOpt();
	}
	
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public Player getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}
	public Map getActiveMap() {
		return activeMap;
	}
	public void setActiveMap(Map activeMap) {
		this.activeMap = activeMap;
	}	
}