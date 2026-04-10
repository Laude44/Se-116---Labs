public class GameManager {

    //Methods
    public void applyDamage(Damageable d, int amount){
            d.takeDamage(amount);
    }
    public void repairObject(Repairable r, int amount){
            r.repair(amount);
    }
    public void upgradeObject(Upgradeable u){
            u.upgrade();
    }




}
