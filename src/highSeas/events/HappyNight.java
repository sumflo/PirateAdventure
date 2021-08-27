package highSeas.events;

import highSeas.controller.Dice;
import highSeas.crew.Captain;
import highSeas.enums.Rum;

public class HappyNight {

    public void feast(Captain captain, Dice dice){

        System.out.println("Captain " + captain.getName() + " is very happy that they won the battle and also get loot. \n" +
                "Let's celebrate! - he shouts, firing a cannon in delight. \n" +
                "The crew makes a loud battle cry - For Captain " + captain.getName() + "! \n" +
                "The celebration lasts all night. The sea resounds with cheerful pirate songs. \n");

        singSong(captain, dice);

        while (captain.getRumOwned() > 0 && captain.getShip().getCrew().get(0).getDrunkLevel().level < 3){
            captain.giveRumToEveryone();
            singSong(captain, dice);
        }

    }

    private void singSong(Captain captain, Dice dice){

        int verse = dice.throwDice6();

        if(captain.getDrunkLevel().equals(Rum.blind_drunk)){
            System.out.println("What will we do with a drunken sailor?\n" +
                    "What will we do with a drunken sailor?\n" +
                    "What will we do with a drunken sailor?\n" +
                    "Early in the morning!\n" +
                    "\n" +
                    "Way hay and up she rises\n" +
                    "Way hay and up she rises\n" +
                    "Way hay and up she rises\n" +
                    "Early in the morning!\n");
        }else if( verse == 1){
            System.out.println("Yo ho, yo ho, a pirate's life for me\n" +
                    "We pillage, we plunder, we rifle, and loot\n" +
                    "Drink up, me 'earties, yo ho\n" +
                    "We kidnap and ravage and don't give a hoot\n" +
                    "Drink up me 'earties, yo ho\n");
        }else if(verse == 2){
            System.out.println("Yo ho, yo ho, a pirate's life for me\n" +
                    "We extort, we pilfer, we filch, and sack\n" +
                    "Drink up, me 'earties, yo ho\n" +
                    "Maraud and embezzle, and even high-jack\n" +
                    "Drink up, me 'earties, yo ho\n");
        }else if(verse == 3){
            System.out.println("Yo ho, yo ho, a pirate's life for me\n" +
                    "We kindle and char, inflame and ignite\n" +
                    "Drink up, me 'earties, yo ho\n" +
                    "We burn up the city, we're really a fright\n" +
                    "Drink up, me 'earties, yo ho\n");
        }else if(verse == 4){
            System.out.println("We're rascals, scoundrels, villans, and knaves\n" +
                    "Drink up, me 'earties, yo ho\n" +
                    "We're devils and black sheep, really bad eggs\n" +
                    "Drink up, me 'earties, yo ho\n");
        }else if(verse == 5){
            System.out.println("Yo ho, yo ho, a pirate's life for me\n" +
                    "We're beggars and blighters, ne'er-do-well cads\n" +
                    "Drink up, me 'earties, yo ho\n" +
                    "Aye, but we're loved by our mommies and dads\n" +
                    "Drink up, me 'earties, yo h\n");
        }else{
            System.out.println("Yo, Ho haul together, hoist the colours high\n" +
                    "Heave ho, thieves and beggars, never shall we die\n" +
                    "Yo, Ho haul together, hoist the colours high\n" +
                    "Heave ho, thieves and beggars, never shall we die\n");
        }
    }

}
