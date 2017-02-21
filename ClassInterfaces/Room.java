import java.util.List;

//this class provides the building blocks of a map
public class Room {
	
	//each room may contain a number of items or a monster
	private List<Item> items;
	private Monster monster;
	
	public Room() {
		
	}
	
	//this method adds an item to the room
	public void addItem(Item item) {

	}
	
	//this method adds a monster to room
	public void addMonster(Monster monster) {

	}
	
	//this method returns the items, if any
	public List<Item> getItems() {
		return items;
	}
	
	//this method returns the monsters if any
	public Monster getMonster() {
		return monster;
	}
	
	//this method returns a description of the room
	public String toString() {
	}

}
