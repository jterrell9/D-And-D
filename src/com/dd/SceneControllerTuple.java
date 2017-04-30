package com.dd;

import com.dd.controller_util.GameSceneController;
import javafx.scene.Scene;

public class SceneControllerTuple {
    private Scene scene;
    private GameSceneController controller;

    public SceneControllerTuple(Scene scene, GameSceneController controller){
        this.scene = scene;
        this.controller = controller;
    }

    public Scene getScene(){
        return scene;
    }

    public GameSceneController getController() {
        return controller;
    }
}
