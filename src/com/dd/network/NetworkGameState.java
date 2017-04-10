package com.dd.network;

import com.dd.entities.Monster;
import com.dd.entities.Player;
import com.dd.items.Item;
import com.dd.levels.DungeonMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NetworkGameState {
    protected String name;
    protected Map<Integer, Player> allActivePlayers = new HashMap<Integer, Player>();
    protected Map<Integer, Item> gameItems = new HashMap<Integer, Item>();
    protected Map<Integer, Monster> gameMonster = new HashMap<Integer, Monster>();
    protected DungeonMap map;

    public NetworkGameState(String name){
        this.name = name;
    }

    public NetworkGameState(String name, DungeonMap map) {
        this.name = name;
        this.map = map;
    }

    public Collection<Player> getPlayerList(){
        return allActivePlayers.values();
    }

    public DungeonMap getMap(){
        return map;
    }

    public void setMap(DungeonMap map){
        this.map = map;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void addActivePlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player passed to GameState is null. Addition failed.");
        }
        Integer index = player.getIndex();
        if (allActivePlayers.containsKey(index)){
            throw new IllegalArgumentException("Player \""
                    + player.getName()
                    + "is already active in this GameState. Addition failed.");
        }
        allActivePlayers.put(index, player);
    }

    public void removeActivePlayer(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Player passed to GameState is null. Removal failed.");
        }
        if(allActivePlayers.remove(player.getIndex()) == null) {
            throw new IllegalArgumentException("Player \""
                    + player.getName()
                    + "is not active in this GameState. Removal failed.");
        }
    }

    public void removeActivePlayerAtIndex(int index){
        if(allActivePlayers.remove(index) == null){
            throw new IllegalArgumentException("Player with index \""
                    + Integer.toString(index)
                    + "is not active in this GameState. Removal failed.");
        }
    }

    public Player getPlayerAtIndex(int index){
        Player retPlayer = allActivePlayers.get(index);
        if(retPlayer == null){
            throw new IllegalArgumentException("Player with index \""
                    + Integer.toString(index)
                    + "is not active in this GameState. Get failed.");
        }
        return retPlayer;
    }

    public Item getItemAtIndex(int index){
        return gameItems.get(index);
    }

    public void setItemWithIndex(int index, Item item){
        if(gameItems.containsKey(index)){
            //throw
        }
        gameItems.put(index, item);
    }

    public Monster getMonsterAtIndex(int index){
        return gameMonster.get(index);
    }

    public void setMonsterAtIndex(int index, Monster monster){
        if(gameMonster.containsKey(index)){
            //throw
        }
        gameMonster.put(index, monster);
    }
}
