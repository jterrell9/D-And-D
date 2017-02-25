public abstract class CommandHandler{
    //Used to initialize the CommandHandler.
    //This will be called right after the 
    //CommandHandler is created.
    public static void init(Object objs[]);

    //Used to handle the command that this
    //handler is registered with
    public void handleCommand(String args[]);
}
