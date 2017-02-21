public interface Monster{
    Stat stats;

    public void attack(Player p1); // basic attack method which all monsters will use
    public void die(); // basic die method which all monsters will use

}