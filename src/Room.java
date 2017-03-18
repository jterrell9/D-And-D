
import java.util.ArrayList;

public class Room {
	
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String COLOR_BLACK = "\u001B[30m";
	public static final String COLOR_RED = "\u001B[31m";
	
	private ArrayList<Item> itemList;
	private Monster monster;
	
	
	public Room() {
		monster=null;
		itemList=new ArrayList<Item>();
	}
	
	public void examine(){
		if(isEmpty()){
			System.out.println("This room is empty");
			return;
		}
		if(monster!=null){
			System.out.println("There is a "+monster.name+" in this room!");
		}
		System.out.println("This room has the following items:\n");
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i)!=null){
				System.out.println("Item "+(i+1)+": "+itemList.get(i).toString());
			}
		}
	}
	public boolean isEmpty(){
		return itemList.isEmpty();
	}
	public int getItemIndex(){
		return itemList.size()-1;
	}
	
	public void addItem(Item item){
		itemList.add(item);
	}
	public void removeItem(Item item){
		if(!itemList.remove(item)){
			System.out.println(COLOR_RED+"!e:removeItem() - item not found in inventory"+COLOR_RESET);
		}
	}
	public void removeItem(int index){
		if(!itemList.remove(itemList.get(index))){
			System.out.println(COLOR_RED+"!e:removeItem - item index not found in inventory"+COLOR_RESET);
		}
	}
	public void removeMonster(){
		monster=null;
	}
	public boolean hasMonster(){
		return monster!=null;
	}
	
	public Item getItem(int index){
		if(index>=0 && index<itemList.size()){
			return itemList.get(index);
		}
		System.out.println(COLOR_RED+"!e:getItem - item not found in inventory"+COLOR_RESET);
		return null;
	}
	public void setMonster(Monster monster){
		this.monster=monster;
	}
	public Monster getMonster(){
		return monster;
	}
	
	public String itemsToString(){
		StringBuilder itemStr=new StringBuilder();
		for(int i=0;i<itemList.size() && itemList.get(i)!=null;i++){
			itemStr.append(itemList.get(i).toString()+"\n");
		}
		return itemStr.toString();
	}
	public String toString(){
		return "Items:\n"+itemsToString()+"Monster: "+monster.toString();
	}
}
