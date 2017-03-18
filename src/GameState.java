public class GameState {
	public Player player;
	public DungeonMap dungeon;
	
	public GameState(Player p1,DungeonMap dm1){
		dungeon=dm1;
		player=p1;
	}
	public GameState(Player p1){
		dungeon=new Maze5x5();
		player=p1;
	}
	public GameState(){
		dungeon=new Maze5x5();
		player=new Player("Player",new MapPosition(),new Stats());
	}
}
