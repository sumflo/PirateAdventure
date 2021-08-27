package highSeas.controller;

import highSeas.crew.Captain;
import highSeas.events.Kokko;
import highSeas.events.Kraken;
import highSeas.events.RepairBoat;
import highSeas.events.Storm;

public class EventController {

    public void randomEvent(Captain captain, Dice dice){

        int randInt = (int) ((Math.random() * (1001 - 1)) + 1);

        if (randInt < 501){
            RepairBoat repairBoat = new RepairBoat();
            repairBoat.repairBoat(captain);
        }else if(randInt < 801){
            Storm storm = new Storm();
            storm.storm(captain, dice);
        }else if(randInt < 901){
            Kokko kokko = new Kokko();
            kokko.theKokkoWillTakeYou(captain);
        }else{
            Kraken kraken = new Kraken();
            kraken.krakenAppears(captain, dice);
        }

    }

}
