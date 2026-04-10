public class Building implements Damageable,Repairable,Upgradeable {
    //Field
    private String type;
    private int level;
    private int health;
    //constructure


    public Building(String type, int level, int health) {
        this.type = type;
        this.level = level;
        this.health = health;
    }

    //Methods
    @Override
    public void takeDamage(int amount){
        health-=amount;
        System.out.println(type+" building took "+ amount+" damage. Remaining health : "+ health+" ");
    }
    @Override
    public boolean isDestroyed(){
        return health <= 0;
    }
    @Override
    public void repair(int amount){
        health+=amount;
        System.out.println(type+" building repaired  current health : "+ health+" . ");
    }
    @Override
    public void upgrade(){
        level++;
        System.out.println(type+" building upgraded to level "+level+ " . ");
    }


}
