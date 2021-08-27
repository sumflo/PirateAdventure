package highSeas.events;

import highSeas.controller.Dice;
import highSeas.crew.Captain;

import java.util.Scanner;

public class EndGame {

    public void theLastAdventure(Captain captain, Dice dice){

        System.out.println("Winding the azure sea beyond dangerous waters, beyond murderous vortices, beyond \n" +
                "the cliffs of sirens on the edge of the world an island appears on the horizon. \n " +
                " - Captain! Land on the horizon! - howls "+ captain.getShip().getCrew().get(4) + ".\n" +
                " - WRAAA!!! - roars the crew. \n" +
                " - Pirates, we're docking! - shouts Captain " + captain.getName() + ".\n");

        System.out.println("A few hours later... \n");

        System.out.println("In the belly of the island, the ship’s crew finds the treasure cave, \n" +
                "where an angel-faced woman stands among a myriad of gems. \n");

        Scanner scanner = new Scanner(System.in);
        int interaction;

        do{
            System.out.println("Captain " + captain.getName() + " ... \n" +
                    "(1) kills the woman, and takes the many treasures with him to the ship.\n" +
                    "(2) ignoring the woman, tries to take the treasures from the island with him.\n" +
                    "(3) takes the woman with him, and also the treasures.\n" +
                    "(4) stays on the island with the woman. Let his crew sail away with the treasure.\n");

            interaction = scanner.nextInt();

        }while(!isValidEnd(interaction));

        if(interaction == 1){
            bloodHandedCaptain(captain);
        }else if(interaction == 2){
            rebellion(captain);
        }else if(interaction == 3){
            womenTreasure(captain);
        }else{
            pirateVillage(captain);
        }

    }

    private boolean isValidEnd(int interaction){
        if(interaction > 0 && interaction < 5){
            return true;
        }else {
            return false;
        }
    }

    private void bloodHandedCaptain(Captain captain){

        captain.setName("Blood-Handed " + captain.getName());

        System.out.println(captain.getName() + " was rumored to be killing innocent women. \n" +
                "Within a few months, he had become the most sought-after criminal and a public enemy. \n" +
                "Countries around the world have sent fleets in his wake and are sure to be hunted down sooner or later...");
    }

    private void rebellion(Captain captain){

        System.out.println(captain.getName() + " was intoxicated by the many treasures. His crew soon revolted " +
                "against him and was exposed to an uninhabited island.");
    }

    private void womenTreasure(Captain captain){

        System.out.println(captain.getName() + " and the angel-faced woman fell in love. \n" +
                "In vain did the woman ask the captain to give up his violent life so far, the captain didn't listen to her. \n" +
                "Shortly after that, in a battle, the women suffered a fatal injury. \n" +
                "Captain" + captain.getName() + " has since blamed himself for the death of his love.");
    }

    private void pirateVillage(Captain captain){
        System.out.println();
    }
}
