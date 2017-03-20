package com.dd.gamescene_util;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public abstract class GameScene extends Scene {
    private StackPane layout;

    public GameScene(StackPane layout, int w, int h){
        super(layout, w, h);
    }
    public abstract void setup(Object[] args);
}