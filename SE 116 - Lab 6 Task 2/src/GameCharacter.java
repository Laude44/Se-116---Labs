public class GameCharacter implements Damageable, Upgradeable {
    //Field
    private String name;
    private int health;
    private int level;
    //constructure


    public GameCharacter(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    //methods
    @Override
    public void takeDamage(int amount){
        health-=amount;
        System.out.println("Character "+ name + " took "+amount+" damage. Remaining health : " + health);
    }

    @Override
    public boolean isDestroyed(){
        return health <= 0;
    }
    @Override
    public void upgrade(){
        level++;
        health+=20;
        System.out.println("Character "+name+" leveled up to "+level+ ". Health: "+health+" . ");
    }



}
