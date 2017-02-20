
public class Room {
	private Item[] items;
	private Monster monster;
	public Room() {
		items=new Item[3];
		monster=null;
	}
	public Item addItem(Item I){
		int i=0;
		while(items[i]!=null){
			i++;
		}
		if(i<3)
			return items[i]=I;
		else{
			System.out.println("ERROR there are already 3 items in this room.");
			return null;
		}
	}
	public Monster addMonster(Monster M){
		if(monster!=null)
			return monster=M;
		else{
			System.out.println("ERROR there is already a monster in this room.");
			return monster;
		}
	}
	public String itemsToString(){
		StringBuilder itemStr=new StringBuilder();
		for(int i=0;i<3 && items[i]!=null;i++){
			itemStr.append(items[i].toString()+"\n");
		}
		return itemStr.toString();
	}
	public String toString(){
		return "Items:\n"+itemsToString()+"Monster: "+monster.toString();
	}
}
