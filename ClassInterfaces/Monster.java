public abstract class Monster{
    private Stats stats;
    private boolean isAlive;
    private boolean isInFight;

    public abstract void attack(Player player); // basic attack method which all monsters will use
    public abstract void die(); // basic die method which all monsters will use
    public abstract void takeDamage(int damage); // reduces health when a monster takes damage
    
    // returns stats
    public Stats getStats(){
    }

    // returns if the monster is alive
    public boolean isAlive(){
    }

    //returns if the monster is in a fight
    public boolean isInFight(){
    }
    
    //sets the stats
    //@post: this.stats = stats;
    public void setStats(Stats stats){
    }

    //sets the variable fight
    //@post: this.isInFight = fight;
    public void setFight(boolean fight){
    }
}
