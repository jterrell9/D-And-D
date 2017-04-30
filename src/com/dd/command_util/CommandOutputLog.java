package com.dd.command_util;

import javafx.scene.control.TextInputControl;

public class CommandOutputLog {
    private TextInputControl output;

    public CommandOutputLog(TextInputControl output){
        this.output = output;
    }

    public void print(String outputStr){
        output.appendText(outputStr);
    }
}
