package com.dd.network;

import com.dd.entities.Player;
import com.dd.levels.DungeonMap;

import java.util.Collection;
import java.util.Map;

public class ServerGameState extends NetworkGameState {
    private Map<Integer, Player> allRegisteredPlayers;
    private int maxNumPlayers;
    private int playerCounter;


    public ServerGameState(String name, Map<Integer, Player> playerList, DungeonMap map, int maxNumPlayers){
        super(name, map);
        this.name = name;
        this.allRegisteredPlayers = playerList;
        this.map = map;
        this.maxNumPlayers = maxNumPlayers;
        this.playerCounter = allRegisteredPlayers.size();
    }

    public void registerPlayer(Player player) throws PlayerLimitReachedException{
        if(player == null) {
            throw new IllegalArgumentException("Player passed to ServerGameState is null. Registration failed.");
        }
        if(playerCounter + 1 < maxNumPlayers) {
            throw new PlayerLimitReachedException("");
        }
        player.setIndex(playerCounter);
        if(!allRegisteredPlayers.containsKey(player)) {
            allRegisteredPlayers.put(playerCounter, player);
        }
        else {
            throw new IllegalArgumentException("Player already registered to this GameState. Registration failed.");
        }
        ++playerCounter;
    }

    public void unregisterPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player passed to ServerGameState is null. Unregistration failed.");
        }
        if (allRegisteredPlayers.remove(player.getIndex()) == null) {
            throw new IllegalArgumentException("Player not registered in this GameState. Unregistration failed.");
        }
    }

    public void addActivePlayer(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Player passed to ServerGameState is null. Addition failed.");
        }
        Integer index = player.getIndex();
        if(!allActivePlayers.containsKey(index)
                && allRegisteredPlayers.containsKey(index)){
            allActivePlayers.put(index, player);
        }
        else{
            throw new IllegalArgumentException("Player already active in this GameState. Addition failed.");
        }
    }

    public void removeActivePlayer(Player player) {
        if(player == null) {
            throw new IllegalArgumentException("Player passed to ServerGameState is null. Removal failed.");
        }
        if(allActivePlayers.remove(player.getIndex()) == null){
            throw new IllegalArgumentException("Player is not currently active in this GameState. Removal failed.");
        }
    }

    public Collection<Player> getActivePlayersList(){
        return allActivePlayers.values();
    }

    public Collection<Player> getRegisteredPlayersList() { return allRegisteredPlayers.values(); }

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

    public int getMaxNumPlayers(){
        return maxNumPlayers;
    }

    public void setMaxNumPlayers(int maxNumPlayers){
        this.maxNumPlayers = maxNumPlayers;
    }

    public class PlayerLimitReachedException extends Exception{
        public PlayerLimitReachedException(String message){
            super(message);
        }
    }
}
