import java.util.List;

public class GameState{
    //List of all active players
    private List<Player> playerList;
    //Currently active player
    private Player activePlayer;
    //GameMap in use
    private GameMap map;
    //Console in use
    private Console console;
    
    //Used to construct the GameState
    public GameState(ArrayList<Player> playerList,
                            Player activePlayer,
                            GameMap map,
                            Console console){
    }

    //Used to get the list of Playes in the game
    public ArrayList<Player> getPlayerList(){
    }

    //Used to get the activePlayer in the game
    public Player getActivePlayer(){
    }

    //Used to get the Map that the game uses
    public GameMap getMap(){  
    }

    //Used to get the Console the game uses
    public Console getConsole(){
    }
}
