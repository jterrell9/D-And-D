public interface Monster{
    Stats stats;
    boolean alive;
    boolean fight;

    public void attack(Player p1); // basic attack method which all monsters will use
    public void die(); // basic die method which all monsters will use
    public void takeDamage(int damage); // reduces health when a monster takes damage
    public Stats getStats(); // returns the stats of the Monster

}