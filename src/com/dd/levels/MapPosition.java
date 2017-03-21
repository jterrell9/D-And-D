package com.dd.levels;

public class MapPosition {
	private int x;
	private int y;

	public MapPosition(){
		y = 0;
		x = 0;
	}
	public MapPosition(int xPos,int yPos){
		y = yPos;
		x = xPos;
	}

	public MapPosition getNorth(){
		return new MapPosition(x,y+1);
	}

	public MapPosition getSouth(){
		return new MapPosition(x,y-1);
	}

	public MapPosition getEast(){
		return new MapPosition(x+1,y);
	}

	public MapPosition getWest(){
		return new MapPosition(x-1,y);
	}

	public int getX(){
		return x;
	}

	public void setX(int x){
		this.x = x;
	}

	public int getY(){
		return y;
	}

	public void setY(int y){
		this.y = y;
	}
}
