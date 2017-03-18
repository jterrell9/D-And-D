package old;
import java.io.PrintStream;
import java.util.ArrayList;

public class Room {
	PrintStream output=new PrintStream(System.out);
	
	private ArrayList<Item> itemList;
	private Monster monster;
	public Room() {
		monster=null;
		itemList=new ArrayList<Item>();
	}
	public void examine(){
		if(isEmpty()){
			output.println("This room is empty");
			return;
		}
		if(monster!=null){
			output.println("There is a "+monster.getName()+" in this room!");
		}
		output.println("This room has the following items:\n");
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i)!=null){
				output.println("Item "+(i+1)+": "+itemList.get(i).toString());
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
			output.println("ERROR item doesn't exist");
		}
	}
	public void removeItem(int index){
		if(!itemList.remove(itemList.get(index))){
			output.println("ERROR item doesn't exist");
		}
	}
	public Monster addMonster(Monster M){
		if(monster==null)
			return monster=M;
		else{
			System.out.println("ERROR there is already a monster in this room.");
			return monster;
		}
	}
	public void killMonster(){
		monster=null;
	}
	public boolean hasMonster(){
		return monster!=null;
	}
	
	public Item getItem(int index){
		if(index>=0 && index<itemList.size()){
			return itemList.get(index);
		}
		output.println("ERROR item doesn't exist");
		return null;
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
