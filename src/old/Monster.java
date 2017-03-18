package old;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Monster {
	PrintStream output=new PrintStream(System.out);
	
	private String name;
	private Stats stat;
	private boolean isAlive=true;
	private boolean isTurn;
	
	public Monster(String name,int health,int attack,int defense) {
		setName(name);
		stat=new Stats(health,health,attack,defense);
		isAlive=true;
		isTurn=false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAlive(){
		return isAlive;
	}
	public void die(){
		isAlive=false;
		output.println("You just killed a "+this.getName()+"!");
	}
	public void takeDamage(int damage){
		stat.setHealth(stat.getHealth()-damage);
		if(stat.getHealth()<=0){
			die();
		}
	}
	public void attack(Player player) throws FileNotFoundException{
		player.takeDamage(stat.getAttack());
	}
	public void examine(){
		output.println(getName()
				+"\nHealth:\t\t"+stat.getHealth()
				+"\nAttack/Defense:\t"+stat.getAttack()+"/"+stat.getDefense());
	}
	@Override
	public String toString(){
		return name
				+"\nHealth:\t\t"+stat.getHealth()+"/"+stat.getMaxHealth()
				+"\nAttack/Defense:\t"+stat.getAttack()+"/"+stat.getDefense();
	}
	public boolean getIsTurn(){
		return isTurn;
	}
	public boolean isTurn() {
		return isTurn=true;
	}
	public void fightTurn(Player player1,Monster monster1) throws FileNotFoundException {
			output.println(monster1+" attacks");
			attack(player1);
			player1.fightTurn(player1,monster1);
		if(!player1.isAlive()){
			player1.die();
		}
	}

	public void endTurn() {
		this.isTurn = false;
	}
}

class Dragon extends Monster{
	public Dragon(String name,int health,int attack,int defense){
		super(name,health,attack,defense);
	}
}
