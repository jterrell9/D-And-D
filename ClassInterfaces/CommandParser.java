import java.util.Map;

public class CommandParser{
    private Map<String, CommandHandler> commandList;

    public CommandParser(){
    
    }

    public registerCommand(String command, CommandHandler cmdHandler){

    }

    public void parseCommand(String command[]){
    
    }

    public String[] getCommandList(){
    
    }
}

public interface CommandHandler{
    //Called immediately after the handler
    //is registered
    public void init(Object objs[]);

    //Called whenever an appropriate command
    //has been requested
    public void handleCommand(String args[]);
}
