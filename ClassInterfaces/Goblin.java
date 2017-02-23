public class Goblin implements Monster{
    // Stats is used to hold the Goblin's stats
    private Stats stats;
    private boolean alive;
    private boolean fight;

    //Constructor used when Goblin is created for specific rooms when rooms are generated
    public Goblin (int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    }

    //used when Goblin attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        // Goblin will deal damage and do a basic attack to the player
    }

    //used when the Goblin takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when Goblin's health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //set health to 0 and other logic to ensure the battle is over
    }

    // returns stats
    public Stat getStats(){
        return stats;
    }

    // returns if the Goblin is alive
    public boolean isAlive() {
        return alive;
    }

    //returns if the Goblin is in a fight
    public boolean isFight() {
        return fight;
    }

    //sets the variable alive
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //sets the variable fight
    public void setFight(boolean fight) {
        this.fight = fight;
    }

    //sets the stats
    public void setStats(Stats stats) {
        this.stats = stats;
    }
}