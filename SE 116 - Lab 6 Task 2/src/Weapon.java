public class Weapon implements Damageable,Repairable {
    //Field
    private String name;
    private int durability;
    //constructure


    public Weapon(String name, int durability) {
        this.name = name;
        this.durability = durability;
    }

    //Methods
    @Override
    public void takeDamage(int amount){
        durability -=amount;
        System.out.println("Weapon "+ name+" durability reduced to +" + durability +". ");
    }
    @Override
    public boolean isDestroyed(){
        return durability <=0;
    }

    @Override
    public void repair(int amount){
        durability +=amount;
        System.out.println("Weapon "+ name+" repaired  durability: " + durability +" . ");
    }



}
