package com.dd.command_util.command;

import com.dd.GameState;
import com.dd.command_util.CommandHandler;
import com.dd.command_util.CommandOutputLog;
import com.dd.entities.Player;
import com.dd.levels.DungeonMap;

public class AttackCommand extends CommandHandler {
    private Player player;
    private DungeonMap map;

    public AttackCommand(String name, GameState gameState){
        super(name);
        player = gameState.getActivePlayer();
        map = gameState.getMap();
    }

    @Override
    public void handleCommand(String[] args, CommandOutputLog outputLog){

    }
}