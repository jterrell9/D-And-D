package com.dd.command_util;

public abstract class CommandHandler {
    public abstract void handleCommand(String[] args, CommandOutputLog outputLog) throws CommandHandlerException;

    public class CommandHandlerException extends Exception {
        public CommandHandlerException(String message) {
            super(message);
        }
    }
}