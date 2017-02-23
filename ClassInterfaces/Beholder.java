public class Beholder implements Monster{
    // Stats is used to hold the Beholder's stats
    private Stats stats;
    private boolean alive;
    private boolean fight;

    //Constructor used when Beholder is created for specific rooms when rooms are generated
    public Beholder (int health, int attack, int defense){
        //set stats, set alive to true, set fight to false
    }

    //used when Beholder attacks the player
    //pre: if(isFight && isAlive)
    //post: p1.getStats().getHealth() != 0
    public void attack(Player p1){
        // Will use the Beholder's attackHelper method to help decide which attack it is using
    }

    //used only when attack method calls it
    public void attackHelper(Player p1){
        // Logic to determine 1 of 10 different ray attack and then help with damage dealt
        // After determining that attack, will deal one more eye beam from main eye
    }

    //used when the Beholder takes damage
    //pre: if(alive && fight)
    //post: if(stats.getHealth() == 0) => die()
    public void takeDamage(int damage){
        //logic for taking damage
    }

    // used when Beholder's health reaches 0
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

    // returns if the Beholder is alive
    public boolean isAlive() {
        return alive;
    }

    //returns if the Beholder is in a fight
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