package highSeas.events;

import highSeas.crew.Captain;

import java.util.Scanner;

public class Kokko {

    public void theKokkoWillTakeYou(Captain captain){

        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Dense fog descends on the boat. No one sees anything. There's just a weird sound.");
        System.out.println("Oh no! This is The Kókkó!");
        System.out.println("Were you a good baby pirate? - asks KóKKó.");
        System.out.println();

        do{
            System.out.println("(1) Yes, I was a good baby pirate. \n" +
                    "(2) No, I am your worst nightmare!");
            System.out.println();

            choice = scanner.nextInt();

        }while(!isValidForKokko(choice));

        if(choice == 1){
            System.out.println("Uhh, The Kókkó is gone. The fog dissipated. Captain " + captain.getName() +
                    " continued his journey safely in search of the ultimate treasure.\n");
        }else if(choice == 2){
            System.out.println("The Kókkó grabbed Captain " + captain.getName() + " and took him away.");
            captain.setAlive(false);
        }

    }

    private boolean isValidForKokko(int choice){
        if(choice == 1 || choice == 2){
            return true;
        }else{
            System.out.println("The chosen interaction is invalid.");
            return false;
        }
    }
}
