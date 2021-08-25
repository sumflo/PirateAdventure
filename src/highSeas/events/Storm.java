package highSeas.events;

import highSeas.controller.Dice;
import highSeas.crew.Captain;
import highSeas.treasure.Treasure;

import java.util.Scanner;

public class Storm {

    public void storm(Captain captain, Dice dice){

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Dark clouds gather in the sky. The wind is getting stronger. It's raining.");
        System.out.println("A huge storm hits the ship.");
        System.out.println("Captain " + captain.getName() + "! We are waiting for your order! - " +
                captain.getShip().getCrew().get(0).getName() + " shouted in the thunder.");
        System.out.println();

        do{
            System.out.println("(1) Throw out the cargo! \n" +
                    "(2) Pull in the sails and hold on! \n" +
                    "(3) Call The Kókkó! \n" +
                    "(4) Fix the cannons and hide in the middle of the deck!");
            System.out.println();

            choice = scanner.nextInt();

        }while(!isValidForStorm(choice));

        if(choice == 1){
            throwCargo(captain, dice);
        }else if(choice == 2){
            pullSails(captain, dice);
        }else if(choice == 3){
            callKokko(captain);
        }else if(choice == 4){

        }

    }

    private boolean isValidForStorm(int choice){
        if(choice > 0 && choice < 5){
            return true;
        }else{
            System.out.println("The chosen interaction is invalid.");
            return false;
        }
    }

    private void throwCargo(Captain captain, Dice dice){

        System.out.println("The crew threw the cargo into the water.");
        captain.getShip().setCargo(null);

        int event = dice.throwDice6();

        if(event % 2 == 0){
            System.out.println("It didn't help much against the storm. The mast broke in half, the ship suffered " +
                    "severe injuries.");
            captain.getShip().setHitPoint(1);
            System.out.println("HP of " + captain.getShip().getName() + " is : " + captain.getShip().getHitPoint() + ".");
            System.out.println("Condition of " + captain.getShip().getName() + " is : " + captain.getShip().getState() + ".");
            System.out.println();
        }else{
            int lostCannon = (int) ((Math.random() * (3 - 1)) + 1);

            System.out.println("It didn't help much against the storm. The ship suffered severe injuries." + lostCannon + " cannon(s) escaped and fell into the sea.");

            captain.getShip().setNumberOfCannons(captain.getShip().getNumberOfCannons() - lostCannon);
            captain.getShip().setHitPoint(captain.getShip().getHitPoint() - 200);

            if(captain.getShip().getHitPoint() <= 0){
                System.out.println("Unfortunately, the ship couldn't stand the storm and sank with the entire crew and the captain.");
                captain.setAlive(false);
            }else{
                System.out.println("HP of " + captain.getShip().getName() + " is : " + captain.getShip().getHitPoint() + ".");
                System.out.println("Condition of " + captain.getShip().getName() + " is : " + captain.getShip().getState() + ".");
                System.out.println("The number of cannons of " + captain.getShip().getName() + " is : " + captain.getShip().getNumberOfCannons() + ".");
                System.out.println();
            }

        }
    }

    private void pullSails(Captain captain, Dice dice){

        int event = dice.throwDice6();

        if(event % 2 == 0){

            int bonusHP = dice.throwDice6();

            System.out.println("The sails were successfully retracted by the crew. The ship survived the storm " +
                    "without a single scratch. Plus, you don’t even have to wash the deck today.");
            System.out.println("The life points of each crew member were increased by " + bonusHP + ".");

            for (int i = 0; i < captain.getShip().getCrew().size(); i++) {
                captain.getShip().getCrew().get(i).setMaxHealthPoint(captain.getShip().getCrew().get(i).getMaxHealthPoint() + bonusHP);
                captain.getShip().getCrew().get(i).healthRestore();
            }

        }else{

            int minusHP = dice.throwDice6();

            System.out.println("The sails were successfully retracted by the crew. The ship survived the storm, " +
                    "but some cannonball escaped causing injury to all members of the crew. At least, you don’t " +
                    "even have to wash the deck today.");

            for (int i = 0; i < captain.getShip().getCrew().size(); i++) {
                captain.getShip().getCrew().get(i).setHealthPoint(captain.getShip().getCrew().get(i).getHealthPoint() + minusHP);
            }

        }

    }

    private void callKokko(Captain captain){
        System.out.println("The Kókkó is appeared. Grabbed the ship with the entire crew and the captain.");
        captain.setAlive(false);
    }

    private void fixCannons(Captain captain, Dice dice){

    }
}
