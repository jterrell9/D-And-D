package com.dd.gamescene_util.gamescene;

import com.dd.command_util.*;
import com.dd.command_util.command.*;
import com.dd.gamescene_util.GameScene;
import javafx.scene.layout.StackPane;

public class RunningGameScene extends GameScene{
    private CommandParser commandParser = new CommandParser();

    public RunningGameScene(StackPane layout, int w, int h){
        super(layout, w, h);

        CommandHandler attackCmdHandler = new AttackCommand();
        CommandHandler dropCmdHandler = new DropCommand();
        CommandHandler equipCmcHandler = new EquipCommand();
        CommandHandler examineCmdHandler = new ExamineCommand();
        CommandHandler moveCmdHandler = new MoveCommand();
        CommandHandler pickupCmdHandler = new PickupCommand();
        CommandHandler quitCmdHandler = new QuitCommand();
        CommandHandler saveCmdHandler = new SaveCommand();
        CommandHandler useCmdHandler = new UseCommand();

        commandParser.registerCommand("attack", attackCmdHandler);
        commandParser.registerCommand("drop", dropCmdHandler);
        commandParser.registerCommand("equip", equipCmcHandler);
        commandParser.registerCommand("examine", examineCmdHandler);
        commandParser.registerCommand("move", moveCmdHandler);
        commandParser.registerCommand("pickup", pickupCmdHandler);
        commandParser.registerCommand("quit", quitCmdHandler);
        commandParser.registerCommand("save", saveCmdHandler);
        commandParser.registerCommand("use", useCmdHandler);
    }

    @Override
    public void setup(Object[] args){

    }
}