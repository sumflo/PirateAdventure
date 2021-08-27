package highSeas.controller;

import highSeas.characters.PlayableCharacters;
import highSeas.crew.Captain;
import highSeas.enums.Rum;
import highSeas.events.HappyNight;
import highSeas.seas.BattleField;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {

    public void startStory(){

        BattleField battle = new BattleField();
        EventController event = new EventController();
        Dice dice = new Dice();
        HappyNight happyNight = new HappyNight();

        PlayableCharacters chars = new PlayableCharacters();
        chars.setCaptainsShips();
        chars.fillAllShipCargo(dice);
        chars.setCaptainsCrews();

        List<Captain> captainList = chars.getCaptains();

        int round = 10;

        gameMaster_introduction();
        int chosenID = getChosenCaptainID();

        if(chosenID == 0){
            return;
        }

        Captain captain = getCaptain(captainList, chosenID);

        gameMaster_startGame(captain);

        for (int i = round; i > 0; i--) {

            if(!captain.isAlive()){
                break;
            }

            gameMaster_startRound();

            switch (chooseInteraction(captain)){
                case 0:
                    return;
                case 1:
                    battle.shipBattle(captain, getRandomCaptain(captainList), dice);
                    break;
                case 2:
                    event.randomEvent(captain,dice);
                    break;
                case 3:
                    battle.boardBattle(captain, getRandomCaptain(captainList), dice);
                    happyNight.feast(captain, dice);
                    break;
                default:
                    System.out.println("You are the emperor of the life, if you got this far.");
                    return;
            }

        }

        //TODO: A játék lezárása egy lezáró eventel (pl endGame)

    }

    /** A mesélő intro szövege. */
    private void gameMaster_introduction(){
        System.out.println("Welcome Adventurer in the High Seas!");
        System.out.println("If you want to take part in some adventures, sea battles, and want to find the ultimate " +
                "treasure, you are in the best place.");
        System.out.println("To get the ultimate treasure, you can fight water or board battles, but you can even sail " +
                "peacefully waiting for the events that fate will bring in your way.\n");
    }


    /** Választható kapitányok megjelenítése, id bekérése és visszaadása. */
    private int getChosenCaptainID(){
        System.out.println("When you are ready for a new adventure, choose your captain!\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println( "(0) Exit the game.\n" +
                "(1) Bartholomew Roberts\n"+
                "(2) Calico Jack\n"+
                "(3) Csen Ji-sao\n"+
                "(4) Edward Teach\n"+
                "(5) Grace O'Malley\n"+
                "(6) Jeanne de Clisson\n"+
                "(7) Kuo-hszing-je\n"+
                "(8) Sir Francis Drake\n"+
                "(9) Sir Henry Morgan\n"+
                "(10) Zheng Yi Sao\n");
        return scanner.nextInt();
    }

    /**A választott kapitányt adja vissza értékül, és kiveszi a kapitányok listájából.*/
    private Captain getCaptain(List<Captain> captainList, int ID){

        for (int i = 0; i < captainList.size(); i++) {
            if(ID == captainList.get(i).getCaptainID()){
                Captain captain = captainList.get(i);
                captainList.remove(i);
                return captain;
            }
        }

        return null;
    }

    private Captain getRandomCaptain(List<Captain> captainList){

        Random rand = new Random();
        Captain randomCaptain;

        int randInt = rand.nextInt(captainList.size());
        randomCaptain = captainList.get(randInt);
        captainList.remove(randInt);

        return randomCaptain;
    }

    /** A mesélő játékot kezdő szövegének kiíratása, a választott kapitány történelmi hátterének leírásával.*/
    private void gameMaster_startGame(Captain playersCaptain){
        System.out.println("Great choice! Your captain is " + playersCaptain.getName() + "\n" +
                playersCaptain.getStory() + "\n" + //TODO: a szöveget valahogy megregulázni, hogy olvashatóbb legyen, és automatikusan tördelje a sorokat x karakterenként.
                 "\n" +
                "Let the journey begin!" + "\n");
    }

    private void gameMaster_startRound(){
        System.out.println("Winding the azure sea, a pirate ship suddenly appears in the distance...");
    }

    /** Interakció választó menü. A választott interakció számát adja vissza értékül. */
    private int chooseInteraction(Captain captain){

        Scanner scanner = new Scanner(System.in);
        int interaction;

        do {
            if (captain.getDrunkLevel().equals(Rum.bone_dry)) {
                System.out.println("(0) Exit the game.\n" +
                        "(1) ...you attack the approaching ship with cannon fire.\n" +
                        "(2) ...you continue to sail and wait for events.\n" +
                        "(NOT ENOUGH DRUNK LEVEL) ...you grab the pirate ship and fight the crew.\n");
            } else {
                System.out.println("(0) Exit the game.\n" +
                        "(1) ...you attack the approaching ship with cannon fire.\n" +
                        "(2) ...you continue to sail and wait for events.\n" +
                        "(3) ...you grab the pirate ship and fight the crew.\n");
            }

            interaction = scanner.nextInt();
        } while(!isValid(interaction,captain)); // ha nem valid az interakció, akkor a ciklus újra fut

        return interaction;
    }

    private boolean isValid(int interaction, Captain captain){
        if (captain.getDrunkLevel().equals(Rum.bone_dry) && interaction > -1 && interaction < 3) {
            return true;
        } else if (interaction >-1 && interaction < 4){
            return true;
        }else{
            System.out.println("The chosen interaction is invalid.");
            return false;
        }
    }

}
