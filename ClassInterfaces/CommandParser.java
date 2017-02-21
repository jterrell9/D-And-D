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
    public void handleCommand(String args[]);
}
