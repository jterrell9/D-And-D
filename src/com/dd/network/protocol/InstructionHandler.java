package com.dd.network.protocol;

import com.dd.command_util.CommandOutputLog;
import com.dd.dd_util.BitPattern;
import com.dd.dd_util.BitSequence;
import com.dd.network.NetworkGameState;

public abstract class InstructionHandler {
    protected BitPattern bitPattern;
    public abstract void handleInstruction(BitSequence instruction, CommandOutputLog outputLog);

    public class InvalidInstructionException extends RuntimeException{
        public InvalidInstructionException(String message){
            super(message);
        }
    }
}
