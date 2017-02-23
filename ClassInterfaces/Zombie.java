public class Zombie implements Monster{
    // Stats is used to hold the Zombie's stats
    private Stats stats;
    private boolean alive;
    private boolean fight;

    //Constructor used when Zombie is created for specific rooms when rooms are generated
    public Zombie (int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    }

    //used when Zombie attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        // Zombie will deal damage and do a basic attack to the player
    }

    //used when the Zombie takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when Zombie's health reaches 0
    // pre: if(getStats().getHealth() == 0)
    // pre: if(alive)
    // post: fight = false
    // post: alive = false
    public void die(){
        //25% chance the zombie comes back to life, increasing as the zombie dies more
    }

    // returns stats
    public Stat getStats(){
        return stats;
    }

    // returns if the Zombie is alive
    public boolean isAlive() {
        return alive;
    }

    //returns if the Zombie is in a fight
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