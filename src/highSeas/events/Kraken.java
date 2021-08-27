package highSeas.events;

import highSeas.controller.Dice;
import highSeas.crew.Captain;

public class Kraken {

    public void krakenAppears(Captain captain, Dice dice){
        System.out.println("Surprise Motherfucker!");
    }

    /*private List<String> events = new ArrayList<>();

    public void krakenAttack(Captain captain){
        System.out.println("Suddenly the sea begins to rage. Nowhere is a cloud in the sky, not even the wind blowing." +
                " What's happening?! A giant tentacle rises from the depths. This is the Kraken!");

        System.out.println("What would you like to do?");

        System.out.println("(1) You throw a cannon into the water to lighten the ship and try to leave behind the Kraken.");
        System.out.println("(2) You don't do anything, you wait for what happens.");
        System.out.println("(3) You open a full cannon fire at the Kraken.");

        for (int i = 0; i < captain.getShip().getCargo().size(); i++) {
            if(captain.getShip().getCargo().get(i).getTreasureID() == 1){
                System.out.println("(4) You take the 'a bottle of earth' in your hands and raise it high to threaten the Kraken.");
            }
        }

    }*/

}
