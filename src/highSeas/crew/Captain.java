package highSeas.crew;

import highSeas.controller.Dice;
import highSeas.enums.Rum;
import highSeas.ship.Ship;

public class Captain extends Pirate{

    private Ship ship;
    private int rumOwned;
    private int fightCounter;
    private String story;
    private boolean alive = true;

    public Captain() {
    }

    //TODO: toString
    public String toString(){

        String nl = System.getProperty("line.separator");

        String captainData = "Name: " + this.getName()+ nl
                + "----------------------------------------" + nl
                + "ID: " + this.getCaptainID() + nl
                + "Strength: " + this.getStrength() + nl
                + "Max HP: " + this.getMaxHealthPoint() + nl
                + "HP: " + this.getHealthPoint() + nl
                + "Drunk level: " + this.getDrunkLevel() + nl
                + "Can Fight? - " + this.isCanFight() + nl
                + "Ship: " + this.ship.getName() + nl
                + "Rum in stock: " + this.rumOwned + nl
                + "Won fights: " + this.fightCounter + nl
                + "Story: " + this.story + nl
                + "Is he alive? - " + this.alive + nl;

        return captainData;
    }

    public void attack(Captain enemyCaptain, Dice dice){

        int damage = (this.getStrength() + this.getDrunkLevel().level * (dice.throwDice12()));
        enemyCaptain.setHealthPoint(enemyCaptain.getHealthPoint() - (this.getStrength() + damage));

        System.out.println("Captain" + this.getName() + " damaged " + damage + ".");
        System.out.println("Captain" + enemyCaptain.getName() + " has " + enemyCaptain.getHealthPoint() + " HP now.");
        System.out.println();
    }

    /** az enemy rumkészletét nem szükséges 0-ra állítani, mivel nem foglalkozunk vele a továbbiakban.*/
    public void loot(Captain enemyCaptain){
        int rumLoot = enemyCaptain.getRumOwned();
        this.rumOwned += rumLoot;
    }

    public void giveRumToEveryone(){

        for (int j = 0; j < this.ship.getCrew().size(); j++) {
            giveRumToPirate(this.ship.getCrew().get(j));
        }
        drinkToCrew();

    }

    private void giveRumToPirate(Pirate happyPirate){

        if(this.rumOwned > 0) {
            if (happyPirate.getDrunkLevel().level == 0) {
                happyPirate.setDrunkLevel(Rum.tipsy);
            } else if (happyPirate.getDrunkLevel().level == 1) {
                happyPirate.setDrunkLevel(Rum.drunk);
            } else if (happyPirate.getDrunkLevel().level == 2) {
                happyPirate.setDrunkLevel(Rum.blind_drunk);
            }

            this.rumOwned = this.rumOwned - 1;

        }else{
            System.out.println("The captain doesn't have enough rum in his supplies");
        }

        System.out.println(happyPirate.getName() + " is " + happyPirate.getDrunkLevel() + "."); // csak önellenőrzés

    }

    private void drinkToCrew(){
        if(this.rumOwned > 0) {

            if (this.getDrunkLevel().level == 0) {
                this.setDrunkLevel(Rum.tipsy);
            } else if (this.getDrunkLevel().level == 1) {
                this.setDrunkLevel(Rum.drunk);
            } else if (this.getDrunkLevel().level == 2) {
                this.setDrunkLevel(Rum.blind_drunk);
            }

            this.rumOwned = this.rumOwned - 1;

        }else{
            System.out.println("The captain doesn't have enough rum in his supplies");
        }

        System.out.println("Captain " + this.getName() + " is " + this.getDrunkLevel() + "."); // csak önellenőrzés
        System.out.println();
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public int getRumOwned() {
        return rumOwned;
    }

    public void setRumOwned(int rumOwned) {
        this.rumOwned = rumOwned;
    }

    public int getFightCounter() {
        return fightCounter;
    }

    public void setFightCounter(int fightCounter) {
        this.fightCounter = fightCounter;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
