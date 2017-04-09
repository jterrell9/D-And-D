package com.dd.command_util;

public abstract class CommandHandler {
	
	public abstract void handleCommand(String commandName, String[] args, CommandOutputLog outputLog) throws InvalidArgumentException;

	protected String getArgsString(String args[]){
        String argsStr = "";
        for(int i = 0; i < args.length - 1; i++) {
            argsStr += args[0] + " ";
        }
        argsStr += args[args.length - 1];
        return argsStr;
    }
	
	public class InvalidArgumentException extends Exception {
		public InvalidArgumentException(String message){
    		super(message);
		}
    	
    	@Override
		public String toString() {
			return super.toString().substring(61);
		}
	}
}