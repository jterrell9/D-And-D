import java.util.Map;

public class CommandParser{
    //Map of registered commands
    private Map<String command, CommandHandler> commandMap;

    //Simple constructor
    public CommandParser(){   
    }

    //Used to register a new command with the command parser
    //pre: !commandMap.keySet().contains(cmd)
    //      && !commandMap.values().contains(cmdHandler)
    //post: Key/Value pair cmd/cmdHandler is added to
    //      commandMap
    public void registerCommand(String cmd, CommandHandler cmdHandler){
    }

    //Used to parse a given command and pass the remaining
    //      arguments to the appropriate CommandHandler
    //pre: commandMap.keySet().contains(command[0])
    //post: The appropriate command is called and the
    //      command arguments are passed
    public void parseCommand(String command[]){
    }

    //Used to get the list of registered commands
    public String[] getCommandList(){  
    }
}
