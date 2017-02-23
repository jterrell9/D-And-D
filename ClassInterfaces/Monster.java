public interface Monster{
    private Stats stats;
    private boolean alive;
    private boolean fight;

    public void attack(Player p1); // basic attack method which all monsters will use
    public void die(); // basic die method which all monsters will use
    public void takeDamage(int damage); // reduces health when a monster takes damage
    public Stats getStats(); // returns the stats of the Monster
    public boolean isAlive(); // returns if the monster is alive
    public boolean isFight(); // returns if the monser is in a fight
    public void setStats(Stats stats); // sets the mosnters stats
    public void setAlive(boolean alive); // sets if the monser is alive
    public void setFight(boolean fight); // sets if the monser is in a fight
}