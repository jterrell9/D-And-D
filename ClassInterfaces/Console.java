import java.util.List;

public class Console{
    //List of entries that have been put into
    //the console.
    private List<String> entries;
    //Entry currently being written to the Console.
    private String currentEntry;

    //Used to get the list of entries that have
    //      already been entered into the Console.
    public List<String> getEntries(){
    }

    //Used to get the entry currently being written
    //      to the Console.
    public String getCurrentEntry(){
    }

    //Used to write text to the entry currently
    //      being written.
    public void writeToEntryLine(String str){
    }

    //Used to erase a number of characters from
    //      the entry currenly being written to.
    public void eraseFromEntryLine(int numChar){
    }

    //Used to add the entry currently being written
    //      to the Console to the list of entries
    public void enterCurrentEntry(){
    }
}
