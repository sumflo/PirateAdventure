package highSeas.crew;

import highSeas.controller.Dice;
import highSeas.enums.Rum;
import highSeas.ship.Ship;

public class Pirate {

    private String name;
    private int captainID;
    private int strength;
    private int maxHealthPoint;
    private int healthPoint;
    private Rum drunkLevel;
    private boolean canFight;

    public Pirate() {
    }

    public String toString(){

        String nl = System.getProperty("line.separator");

        String pirateData = "Name: " + this.name + nl
                + "----------------------------------------" + nl
                + "Captain's name: " + this.captainID + nl
                + "Strength: " + this.strength + nl
                + "Max HP: " + this.maxHealthPoint + nl
                + "HP: " + this.healthPoint + nl
                + "Drunk level: " + this.drunkLevel + nl
                + "Can Fight? - " + this.canFight;

        return pirateData;
    }

    public void attack(Pirate enemyPirate, Dice dice){
        int damage = this.strength + this.drunkLevel.level * (dice.throwDice6());

        enemyPirate.setHealthPoint(enemyPirate.getHealthPoint() - (damage));

        System.out.println(this.name + " damaged " + damage + ".");
        System.out.println(enemyPirate.getName() + " has " + enemyPirate.getHealthPoint() + " HP now.");
        System.out.println();
    }

    public void healthRestore(){
            this.healthPoint = this.maxHealthPoint;
    }

    public void decreaseDrunkLevel(){
        if(this.drunkLevel.level == 1 && this.captainID != 10){
            this.drunkLevel = Rum.bone_dry;
        }else if(this.drunkLevel.level == 2){
            this.drunkLevel = Rum.tipsy;
        }else if(this.drunkLevel.level == 3){
            this.drunkLevel = Rum.drunk;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaptainID() {
        return captainID;
    }

    public void setCaptainID(int captainID) {
        this.captainID = captainID;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        this.maxHealthPoint = maxHealthPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public Rum getDrunkLevel() {
        return drunkLevel;
    }

    public void setDrunkLevel(Rum drunkLevel) {
        this.drunkLevel = drunkLevel;
    }

    public boolean isCanFight() {
        return canFight;
    }

    public void setCanFight(boolean canFight) {
        this.canFight = canFight;
    }
}
