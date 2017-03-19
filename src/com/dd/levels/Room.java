package com.dd.levels;

import java.util.ArrayList;

import com.dd.GameRunner;
import com.dd.entities.Monster;
import com.dd.items.Item;

public class Room {
	
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
		System.out.println("This room has the following items:");
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
			GameRunner.printLnTitle(' ',"ERROR",24);
			System.out.println("Room.removeItem() - item not found");
		}
	}
	public void removeItem(int index){
		if(!itemList.remove(itemList.get(index))){
			GameRunner.printLnTitle(' ',"ERROR",24);
			System.out.println("Room.removeItem() - item index not found");
		}
	}
	public void removeMonster(){
		monster=null;
	}
	public boolean hasMonster(){
		return monster!=null;
	}
	
	public Item getItem(int index){
		index--;
		if(index>=0 && index<itemList.size()){
			return itemList.get(index);
		}
		GameRunner.printLnTitle(' ',"ERROR",24);
		System.out.println("Room.getItem() - item not found");
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
