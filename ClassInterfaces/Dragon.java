public class Dragon implements Monster{
    // Stats is used to hold the Dragon's stats
    Stats stats;
    boolean alive;
    boolean fight;

    //Constructor used when Dragon is created for specific rooms when rooms are generated
    public Dragon (int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    }

    //used when dragon attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        //decides on which attack to do, either breath attack or claws, and deal damage
        //to player
    }

    //used when the dragon takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when dragon health reaches 0
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

    // returns if the dragon is alive
    public boolean isAlive() {
        return alive;
    }

    //returns if the dragon is in a fight
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