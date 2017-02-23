public abstract class Monster{
    private Stats stats;
    private boolean isAlive;
    private boolean isInFight;

    public abstract void attack(Player p1); // basic attack method which all monsters will use
    public abstract void die(); // basic die method which all monsters will use
    public abstract void takeDamage(int damage); // reduces health when a monster takes damage
    
    // returns stats
    public Stats getStats(){
        return stats;
    }

    // returns if the monster is alive
    public boolean isAlive(){
        return isAlive;
    }

    //returns if the monster is in a fight
    public boolean isInFight(){
        return isInFight;
    }
    
    //sets the stats
    public void setStats(Stats stats){
        this.stats = stats;
    }

    //sets the variable fight
    public void setFight(boolean fight){
        this.isInFight = fight;
    }
}