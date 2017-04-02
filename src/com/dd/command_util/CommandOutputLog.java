package com.dd.command_util;

import javafx.scene.control.TextInputControl;

public class CommandOutputLog {
    private TextInputControl outputLog;

    public CommandOutputLog(TextInputControl outputLog){
        this.outputLog = outputLog;
    }

    public void printToLog(String outputStr){
        outputLog.appendText(outputStr);
    }
}
