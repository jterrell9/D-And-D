public class Map{
    Room[][] rooms;
    int seed;

    public Map(int seed){
        //logic to make the array of rooms through a seed number
        //i.e. procedural generation
    }

    public Room getRoom(int x, int y){
        return rooms[x][y];
    }

    public Room getNextRoon(int x, int y, String direction){
        //logic to return a valid next room
    }
}