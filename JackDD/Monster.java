
public class Monster {
	private String name;
	private Stats stat;
	public Monster(String name,int health,int maxHealth,int attack,int defense) {
		setName(name);
		stat.setHealth(health);
		stat.setMaxHealth(maxHealth);
		stat.setAttack(attack);
		stat.setDefense(defense);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString(){
		return name
				+"\nHealth:\t\t"+stat.getHeath()+"/"+stat.getMaxHealth()
				+"\nAttack/Defense:\t"+stat.getAttack()+"/"+stat.getDefense();
	}

}
